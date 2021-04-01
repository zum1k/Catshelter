package io.kontur.utils;

import java.time.ZonedDateTime;

public interface AuditEntity {
  ZonedDateTime getCreateDate();

  void setCreateDate(ZonedDateTime time);

  ZonedDateTime getLastUpdateDate();

  void setLastUpdateDate(ZonedDateTime time);
}
