package com.capgemini.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Benificiary.
 */
@Entity
@Table(name = "benificiary")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Benificiary implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "account_id")
    private long accountId;

    @Column(name = "display_name")
    private String displayName;

    @ManyToOne
    private BAccount bAccount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public BAccount getBAccount() {
        return bAccount;
    }

    public void setBAccount(BAccount bAccount) {
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
        Benificiary benificiary = (Benificiary) o;
        if(benificiary.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, benificiary.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Benificiary{" +
            "id=" + id +
            ", accountId='" + accountId + "'" +
            ", displayName='" + displayName + "'" +
            '}';
    }
}
