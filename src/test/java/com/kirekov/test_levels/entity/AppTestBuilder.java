package com.kirekov.test_levels.entity;

public class AppTestBuilder {

  private Long id;
  private String name = "appName";
  private Device device = Device.ANDROID;

  public static AppTestBuilder builder() {
    return new AppTestBuilder();
  }

  public App build() {
    return new App()
        .setId(id)
        .setName(name)
        .setDevice(device);
  }

  public AppTestBuilder setId(Long id) {
    this.id = id;
    return this;
  }

  public AppTestBuilder setName(String name) {
    this.name = name;
    return this;
  }

  public AppTestBuilder setDevice(Device device) {
    this.device = device;
    return this;
  }
}