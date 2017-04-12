package com.capgemini.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Transaction.
 */
@Entity
@Table(name = "transaction")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "dot")
    private LocalDate dot;

    @Column(name = "details")
    private String details;

    @Column(name = "amount")
    private double amount;

    @ManyToOne
    private BAccount bAccount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDot() {
        return dot;
    }

    public void setDot(LocalDate dot) {
        this.dot = dot;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public BAccount getBAccount() {
        return bAccount;
    }

    public void setBAccount(BAccount bAccount) {
        this.bAccount = bAccount;
    }

    
    
    public Transaction() {
		super();
	}

	public Transaction(LocalDate dot, String details, double amount, BAccount bAccount) {
		super();
		this.dot = dot;
		this.details = details;
		this.amount = amount;
		this.bAccount = bAccount;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Transaction transaction = (Transaction) o;
        if(transaction.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, transaction.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Transaction{" +
            "id=" + id +
            ", dot='" + dot + "'" +
            ", details='" + details + "'" +
            ", amount='" + amount + "'" +
            '}';
    }
    
}
