package myQuiz.controller.builder;

import myQuiz.model.quiz.Question;
import myQuiz.service.QuestionService;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import java.util.List;
import java.util.Map;

/**
 * User: eluibon
 * Date: 28/03/13
 * Time: 22.18
 */
public class QuestionLazyDataModel extends LazyDataModel<Question> {

    private QuestionService questionService;

    public QuestionLazyDataModel(QuestionService questionService) {

        this.questionService = questionService;
    }

    @Override
    public Question getRowData(String rowKey) {

        return questionService.findById(Long.valueOf(rowKey));
    }

    @Override
    public Object getRowKey(Question question) {

        return question.getId();
    }

    @Override
    public void setRowIndex(int rowIndex) {
        /*
         * The following is in ancestor (LazyDataModel):
         * this.rowIndex = rowIndex == -1 ? rowIndex : (rowIndex % pageSize);
         */
        if (rowIndex == -1 || getPageSize() == 0) {
            super.setRowIndex(-1);
        }
        else {
            super.setRowIndex(rowIndex % getPageSize());
        }
    }

    @Override
    public List<Question> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {

        List<Question> list = questionService.findByFilters(filters);
        this.setRowCount(list.size());
        return list;
    }
}
