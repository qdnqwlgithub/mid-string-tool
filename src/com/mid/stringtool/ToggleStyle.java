package com.mid.stringtool;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.project.Project;
import com.mid.stringtool.util.StringUtil;

import java.util.Objects;

public class ToggleStyle extends AnAction {


    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        Project project = e.getData(PlatformDataKeys.PROJECT);
        Editor editor = e.getData(PlatformDataKeys.EDITOR);

        if (Objects.isNull(project) || Objects.isNull(editor)) {
            return;
        }

        Document document = editor.getDocument();
        // SelectionModel api 在README.md中有介绍
        SelectionModel selectionModel = editor.getSelectionModel();
        selectionModel.selectWordAtCaret(true);

        String selectedText = selectionModel.getSelectedText();

        if (Objects.isNull(selectedText) || selectedText.equals("")) {
            return;
        }

        int start = selectionModel.getSelectionStart();
        int end = selectionModel.getSelectionEnd();


        WriteCommandAction.runWriteCommandAction(project,
                () -> {
                    document.replaceString(start, end, selectedText.contains("_")
                            ? StringUtil.toCamelCase(selectedText) : StringUtil.toUnderScoreCase(selectedText));
                });


    }


}
