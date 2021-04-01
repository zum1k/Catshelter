package io.kontur.entity;

import io.kontur.utils.AuditEntityDateListener;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

@EntityListeners({AuditEntityDateListener.class})
@Data
@NoArgsConstructor
@Table(name = "feeding")
@Entity
public class Feeding extends AbstractEntity<Long> {

}
