package com.example.nat.core.action;



import static com.example.nat.core.utils.AccessibilityUtils.getClickableNode;

import android.os.Build;
import android.view.accessibility.AccessibilityNodeInfo;

import androidx.annotation.RequiresApi;

import com.example.nat.core.models.NodeInfoExtractedModel;
import com.example.nat.core.service.BaseService;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class ClickTextAndVerifyAction extends BaseAction {
    public String verifyTextAppears;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void perform(Object... objects) {
        BaseService accessibilityService = (BaseService) objects[0];

        List<NodeInfoExtractedModel> nodeInfoExtractedModelList = accessibilityService.getNodes(accessibilityService.getRootInActiveWindow(), 0);

        for (NodeInfoExtractedModel nodeInfoExtractedModel : nodeInfoExtractedModelList) {
            if (nodeInfoExtractedModel.getNodeText().trim().contains(inputData.toString())) {
                AccessibilityNodeInfo clickableNode = getClickableNode(nodeInfoExtractedModel.getNodeInfo());
                if (!clickableNode.isClickable()) {
                    failed();
                }
                clickableNode.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                break;
            }
        }
        nodeInfoExtractedModelList = accessibilityService.getNodes(accessibilityService.getRootInActiveWindow(), 0);
        for (NodeInfoExtractedModel nodeInfoExtractedModel : nodeInfoExtractedModelList) {
            if (verifyTextAppears != null && nodeInfoExtractedModel.getNodeText().trim().contains(verifyTextAppears)) {
                success();
                return;
            }
        }

        failed();
    }


}
