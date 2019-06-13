package com.kangaroo.backup.DTO;

import com.kangaroo.backup.Domain.Question;
import com.kangaroo.backup.Domain.Task;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class ShortTaskOutputDTOTest {

    /**
     * 测试Beans copy
     * 属性不同不会处理，应该包括相应get/set
     * Date和List这些复杂类型都可以，尚不知那种复杂类不可？
     */
    @Test
    public void fromTask() {
        Task task = new Task();
        task.setTaskDeadLineDate(new Date());
        task.setTaskId(1);
        task.setTaskTitle("123");
        Question question = new Question();
        question.setOptions("ops");
        question.setQuestionId(23);
        Question question1 = new Question();
        question1.setQuestionId(24);
        question1.setOptions("ops2");
        List<Question> questions = new LinkedList<>();
        questions.add(question);
        questions.add(question1);
        task.setQuestionnaire(questions);
        ShortTaskOutputDTO dto = new ShortTaskOutputDTO();
        BeanUtils.copyProperties(task, dto);
        System.out.println("--" + dto);
        System.out.println("--" + dto.getTaskId());
//        System.out.println("--" + dto.getTaskDeadLineDate());
//        System.out.println("--" + dto.getQuestionnaire());
        System.out.println("--" + dto.getTaskDeadlineDateString());
    }
}