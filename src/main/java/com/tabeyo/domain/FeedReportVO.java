package com.tabeyo.domain;

import java.util.Date;

import lombok.Data;

@Data
public class FeedReportVO {
	
	private Long rptNo;
	private String rptContent;
	private Date rptDate;
	private String reporter;
	private Long fdNo;
	private String restrictionContent;
	private String restrictionReason;
	private Date restrictionDate;
	
}
