package com.kangaroo.backup.Dao;

import com.kangaroo.backup.Domain.Question;

import java.util.List;

public interface QuestionnarieMapper {

    /*保存问卷到数据库*/
    void saveQuestionnarie(List<Question> questions);

    /*通过id删除问卷*/
    void deleteQuestionnarie(int questionId);

}
