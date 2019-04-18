package com.example.servicehi.entity.dto;

import com.example.servicehi.entity.Diary;
import com.example.servicehi.entity.TipsRelation;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Alias("DiaryDto")
@Getter
@Setter
@ToString
public class DiaryDto extends Diary {
    private List<TipsRelation> tipsRelations;
}