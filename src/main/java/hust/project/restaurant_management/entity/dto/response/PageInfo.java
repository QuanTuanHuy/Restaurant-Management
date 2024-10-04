package hust.project.restaurant_management.entity.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageInfo {
    private Long totalPage;

    private Long totalRecord;

    private Long pageSize;

    private Long nextPage;

    private Long previousPage;
}
