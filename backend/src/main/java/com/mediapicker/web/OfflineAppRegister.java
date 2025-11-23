package com.mediapicker.web;

import com.mediapicker.db.DummyDb;
import com.mediapicker.domain.mediathek.Mediathek;
import com.mediapicker.domain.user.User;
import com.mediapicker.service.MediathekService;
import com.mediapicker.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OfflineAppRegister {

  @Value("${app.offline.default.username}")
  private String DEFAULT_USERNAME;

  private final UserService userService;
  private final MediathekService mediathekService;
  private static final Logger log = LoggerFactory.getLogger(OfflineAppRegister.class);

  public OfflineAppRegister(UserService userService,
                            MediathekService mediathekService) {
    this.userService = userService;
    this.mediathekService = mediathekService;
  }

  public void setupApplication() {
    if(!userService.istRegistriertByUsername(DEFAULT_USERNAME)) {
      User user = new User(
        null,
        DEFAULT_USERNAME,
        "1234",
        "default@default.de"
      );
      Mediathek mediathek = DummyDb.initMediathek(user);
      mediathekService.save(mediathek);
      log.info("Mediathek für DEFAULT_USER '%s' erstellt.".formatted(DEFAULT_USERNAME));
    }




  }
}
