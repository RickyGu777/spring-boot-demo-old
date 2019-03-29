package com.example.servicehi.entity.dto;

import com.example.servicehi.entity.ShareTicketUrl;
import com.example.servicehi.entity.TipsRelation;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Alias("ShareTicketUrlDto")
@Getter
@Setter
@ToString
public class ShareTicketUrlDto extends ShareTicketUrl {
    private List<TipsRelation> tipsRelations;
    private String tipsName;
}
