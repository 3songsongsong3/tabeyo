package com.tabeyo.domain;

import java.util.Date;

import lombok.Data;

@Data
public class BusinReportVO {
	private Long reportNo;
	private String reportCon;
	private Date reportDate;
	private Long businNo;
	private String userId;
	private String restrictionContent;
	private String restrictionReason;
	private String restrictionDate;

	
}
