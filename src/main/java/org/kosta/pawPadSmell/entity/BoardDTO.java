package org.kosta.pawPadSmell.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import groovy.transform.builder.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the G_BOARD database table.
 * 
 */
@Data
@Builder
@Getter
@Setter
@Entity
@Table(name="G_BOARD")
public class BoardDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="POST_ID")
	private long postId;

	@Column(name="BOARD_ID")
	private BigDecimal boardId;

	@Column(name="CATEGORY_ID")
	private BigDecimal categoryId;

	@Column(name="COMMENT_COUNT")
	private BigDecimal commentCount;

	@Lob
	private String content;

	private String filename;

	private String filepath;

	private BigDecimal hits;

	@Column(name="MEMBER_ID")
	private BigDecimal memberId;

	private String regdate;

	private String title;



}