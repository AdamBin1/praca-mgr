package com.springmvc.dao.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.dao.StageDAO;
import com.springmvc.model.FieldType;
import com.springmvc.model.IdPropIdType;
import com.springmvc.model.IdSecPair;
import com.springmvc.model.IdValuePair;
import com.springmvc.model.ObjectModel;
import com.springmvc.model.Stage;

@Service
@Transactional
public class StageService {

	@Autowired
	private StageDAO stageDAO;

	@Autowired
	private ObjectService objectService;

	@Autowired
	TextBoxPropValueService textBoxPropValueService;

	@Autowired
	ComboBoxPropValueService comboBoxPropValueService;

	@Autowired
	DateTextBoxPropValueService dateTextBoxPropValueService;

	public StageService() {
	}

	public Stage getStageForId(int id) {
		Stage stage = stageDAO.findById(id);

		stage.updateProperties();

		return stage;
	}

	// wszystkie oprócz tej bez nazwy, bo ona jest main
	public List<Stage> getAllStages() {
		List<Stage> stages = stageDAO.findAll();

		// usuniecie main stage
		for (int i = 0; i < stages.size(); i++) {
			if (stages.get(i).isMainStage()) {
				stages.remove(i);
				break;
			}
		}

		stages.sort((s1, s2) -> s1.getSec() - s2.getSec());
		stages.parallelStream().forEach(s -> s.updateProperties());
		return stages;
	}

	public Stage saveStage(Stage stage) {
		return stageDAO.save(stage);
	}

	public Stage getMainStage() {
		Stage stage = stageDAO.findByName(null);
		if (stage == null) {
			Stage s = new Stage(null, null, null, null, null, null);
			stageDAO.save(s);
			stage = stageDAO.findByName(null);
		}
		stage.updateProperties();
		return stage;
	}

	public boolean isNameInDatabase(Integer id, String name) {
		if (id == null) {
			return stageDAO.countByName(name) > 0;
		} else {
			return stageDAO.countByIdNotAndName(id, name) > 0;
		}
	}

	public boolean isSecInDatabase(Integer id, int sec) {
		if (id == null) {
			return stageDAO.countBySec(sec) > 0;
		} else {
			return stageDAO.countByIdNotAndSec(id, sec) > 0;
		}
	}

	public List<IdSecPair> getIdSecPairList(Stage stage) {
		List<IdSecPair> idSecPairs = new ArrayList<>();
		stage.updateProperties();
		stage.getProperties().parallelStream()
				.forEach(option -> idSecPairs.add(new IdSecPair(option.getId(), option.getSec())));
		return idSecPairs;
	}

	public List<IdPropIdType> getIdPropIdPairList(ObjectModel object) {
		List<IdPropIdType> idPropIdPairs = new ArrayList<>();

		object.getTextBoxPropValues()
				.forEach(pv -> idPropIdPairs.add(new IdPropIdType(pv.getId(), pv.getPropId(), FieldType.TEXT)));
		object.getComboBoxPropValues()
				.forEach(pv -> idPropIdPairs.add(new IdPropIdType(pv.getId(), pv.getPropId(), FieldType.COMBO)));
		object.getDateTextBoxPropValues()
				.forEach(pv -> idPropIdPairs.add(new IdPropIdType(pv.getId(), pv.getPropId(), FieldType.DATE)));

		return idPropIdPairs;
	}

	public void updateValues(Stage stage, int objectId) {
		stage.getTextBoxProperties().forEach(
				prop -> prop.setPropValue(textBoxPropValueService.findByObjectIdAndPropId(objectId, prop.getId())));
		stage.getComboBoxProperties().forEach(
				prop -> prop.setPropValue(comboBoxPropValueService.findByObjectIdAndPropId(objectId, prop.getId())));
		stage.getDateTextBoxProperties().forEach(
				prop -> prop.setPropValue(dateTextBoxPropValueService.findByObjectIdAndPropId(objectId, prop.getId())));
	}

	public List<IdValuePair> getIdAndNamesForAllStages() {
		List<Stage> stages = this.getAllStages();
		List<IdValuePair> idValuePairs = new ArrayList<>(stages.size());
		stages.forEach(s -> idValuePairs.add(new IdValuePair(s.getId(), s.getName())));
		return idValuePairs;
	}

	public Stage getFirstStage() {
		return stageDAO.findFirstByNameNotNullOrderBySec();
	}

	public List<Stage> getMainStageForAllObjects() {
		List<ObjectModel> objects = objectService.getAllObjects();
		List<Stage> stages = new ArrayList<>(objects.size());
		Stage mainStage = this.getMainStage();
		Stage cloned;
		for (ObjectModel object : objects) {
			cloned = mainStage.cloneStageProperties();
			updateValues(cloned, object.getId());
			stages.add(cloned);
		}
		return stages;
	}

}
