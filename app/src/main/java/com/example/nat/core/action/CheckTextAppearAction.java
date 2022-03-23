package com.example.nat.core.action;

import com.example.nat.core.models.NodeInfoExtractedModel;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@SuperBuilder
public class CheckTextAppearAction extends BaseAction {
    @Override
    public void perform(Object... objects) {

        List<NodeInfoExtractedModel> nodeInfoExtractedModelList = (List<NodeInfoExtractedModel>) objects[1];

        for (NodeInfoExtractedModel nodeInfoExtractedModel : nodeInfoExtractedModelList) {
            if (nodeInfoExtractedModel.getNodeText().trim().contains(inputData.toString())) {
                outputData = nodeInfoExtractedModel.getRect();
                success();
                return;
            }
        }
        failed();
    }
}
