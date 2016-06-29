package framework;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)

public @interface AccessControl {
  String allow();
}
