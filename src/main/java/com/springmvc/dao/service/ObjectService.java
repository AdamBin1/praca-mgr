package com.springmvc.dao.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvc.dao.ObjectDAO;
import com.springmvc.model.ObjectModel;
import com.springmvc.model.Stage;

@Service
public class ObjectService {

	@Autowired
	private ComboBoxPropValueService comboBoxPropValueService;

	@Autowired
	private TextBoxPropValueService textBoxPropValueService;

	@Autowired
	private DateTextBoxPropValueService dateTextBoxPropValueService;

	@Autowired
	private StageService stageService;

	@Autowired
	ObjectDAO objectDao;

	public ObjectModel saveObjectAndPropValues(ObjectModel object) {
		ObjectModel savedObject = objectDao.save(object);

		object.getTextBoxPropValues().forEach(tbpv -> {
			tbpv.setObjectId(savedObject.getId());
		});

		savedObject.setTextBoxPropValues(textBoxPropValueService.saveAll(object.getTextBoxPropValues()));

		object.getComboBoxPropValues().forEach(cbp -> {
			cbp.setObjectId(savedObject.getId());
		});

		savedObject.setComboBoxPropValues(comboBoxPropValueService.saveAll(object.getComboBoxPropValues()));

		object.getDateTextBoxPropValues().forEach(dtbpv -> {
			dtbpv.setObjectId(savedObject.getId());
		});

		savedObject.setDateTextBoxPropValues(dateTextBoxPropValueService.saveAll(object.getDateTextBoxPropValues()));

		return savedObject;
	}

	public ObjectModel getObjectById(Integer objectId) {
		return objectDao.findById(objectId);
	}

	public ObjectModel moveToNextStage(int objectId) {
		ObjectModel object = objectDao.findById(objectId);
		List<Stage> stages = stageService.getAllStages();
		Stage stage;
		for (int i = 0; i < stages.size() - 1; i++) {
			stage = stages.get(i);
			if (object.getActiveStageId() == stage.getId()) {
				object.setActiveStageId(stages.get(i + 1).getId());
				object = objectDao.save(object);
				break;
			}
		}
		return object;
	}

	public void setFirstStageAsActive(ObjectModel object) {
		object.setActiveStageId(stageService.getFirstStage().getId());
	}

	public List<ObjectModel> getAllObjects() {
		return objectDao.findAll();
	}

	@Transactional
	public void remove(int objectId) {

		textBoxPropValueService.deleteByObjectId(objectId);
		comboBoxPropValueService.deleteByObjectId(objectId);
		dateTextBoxPropValueService.deleteByObjectId(objectId);

		ObjectModel object = objectDao.findById(objectId);
		objectDao.delete(object);

	}

}
