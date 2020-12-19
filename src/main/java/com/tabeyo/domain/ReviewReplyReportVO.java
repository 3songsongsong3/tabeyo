package com.tabeyo.domain;

import java.util.Date;

import lombok.Data;

@Data
public class ReviewReplyReportVO {
	
	private Long rptNo;
	private String rptContent;
	private Date rptDate;
	private String reporter;
	private Long rvRno;
	private String restrictionContent;
	private String restrictionReason;
	private Date restrictionDate;
	
}
