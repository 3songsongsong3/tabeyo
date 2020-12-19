CREATE SEQUENCE seq_borard;

CREATE TABLE tbl_board(
    bno         NUMBER(10, 0) CONSTRAINT pk_board PRIMARY KEY,
    title       VARCHAR2(200)  NOT NULL,
    content     VARCHAR2(2000) NOT NULL,
    writer      VARCHAR2(50)   NOT NULL,
    regdate     DATE DEFAULT SYSDATE,
    updatedate  DATE DEFAULT SYSDATE
);
    
INSERT INTO tbl_board(bno, title, content, writer)
VALUES(seq_board.NEXTVAL, '테스트 제목', '테스트 내용', 'user00');


   
CREATE TABLE tbl_reply (	
   rno            NUMBER(10, 0) CONSTRAINT pk_reply PRIMARY KEY,	
   bno            NUMBER(10, 0) CONSTRAINT fk_reply  
                                REFERENCES tbl_board(bno)  NOT NULL,	
   reply          VARCHAR2(1000)   NOT NULL,	
   replyer        VARCHAR2(50)     NOT NULL,	
   replyDate      DATE  DEFAULT  SYSDATE,	
   updateDate     DATE   DEFAULT  SYSDATE	
);	

CREATE SEQUENCE seq_reply
START WITH 1
MAXVALUE   99999999999999999999999999
INCREMENT BY 1
NOCACHE
NOCYCLE;   



CREATE INDEX idx_reply ON tbl_reply(bno DESC, rno ASC);



ALTER TABLE tbl_board ADD(replycnt NUMBER DEFAULT 0);					
					
UPDATE tbl_board 					
SET replycnt = ( SELECT COUNT(rno) 					
                       FROM   tbl_reply					
                       WHERE tbl_reply.bno = tbl_board.bno );					



CREATE TABLE tbl_attach (	
   uuid           VARCHAR2(100) CONSTRAINT pk_attach PRIMARY KEY,	
   uploadPath     VARCHAR2(200) NOT NULL,	
   fileName       VARCHAR2(100) NOT NULL,	
   filetype       CHAR(1)       DEFAULT 'I',	
   bno            NUMBER(10, 0) CONSTRAINT fk_board_attach  
                                REFERENCES tbl_board(bno)
);	
























    
    