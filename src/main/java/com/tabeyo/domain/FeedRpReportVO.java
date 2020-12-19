package com.tabeyo.domain;

import java.util.Date;

import lombok.Data;

@Data
public class FeedRpReportVO {
	private Long rptNo;
	private String fdRpContent;
	private Date rptDate;
	private String reporter;
	private Long fdRpNo;
	private String restrictionContent;
	private String restrictionReason;
	private Date restrictionDate;
}
