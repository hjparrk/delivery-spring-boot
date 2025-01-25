package org.delivery.db.account;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.delivery.db.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;


@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account extends BaseEntity {
}
