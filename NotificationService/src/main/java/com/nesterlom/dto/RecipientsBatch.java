package com.nesterlom.dto;

import com.nesterlom.entity.Recipient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecipientsBatch {
    //private Recipient[] recipients;
    private List<Recipient> recipients;
    private String templateName;
//    private long templateId;
}
