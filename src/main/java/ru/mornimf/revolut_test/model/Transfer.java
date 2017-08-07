package ru.mornimf.revolut_test.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "transfers")
public class Transfer {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "account_from_id")
	private Integer accountFrom;
	@Column(name = "account_to_id")
	private Integer accountTo;
	@Column(name = "dt")
	private Date dt;
	@Column(name = "amount")
	private BigDecimal amount;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDt() {
		return dt;
	}

	public void setDt(Date dt) {
		this.dt = dt;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getAccountFrom() {
		return accountFrom;
	}

	public void setAccountFrom(Integer accountFrom) {
		this.accountFrom = accountFrom;
	}

	public Integer getAccountTo() {
		return accountTo;
	}

	public void setAccountTo(Integer accountTo) {
		this.accountTo = accountTo;
	}
	

}
