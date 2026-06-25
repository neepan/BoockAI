package com.neepan.boockai.core.data;

import com.neepan.boockai.core.data.local.ReadingProgressDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class RoomReadingProgressDataSource_Factory implements Factory<RoomReadingProgressDataSource> {
  private final Provider<ReadingProgressDao> progressDaoProvider;

  private RoomReadingProgressDataSource_Factory(Provider<ReadingProgressDao> progressDaoProvider) {
    this.progressDaoProvider = progressDaoProvider;
  }

  @Override
  public RoomReadingProgressDataSource get() {
    return newInstance(progressDaoProvider.get());
  }

  public static RoomReadingProgressDataSource_Factory create(
      Provider<ReadingProgressDao> progressDaoProvider) {
    return new RoomReadingProgressDataSource_Factory(progressDaoProvider);
  }

  public static RoomReadingProgressDataSource newInstance(ReadingProgressDao progressDao) {
    return new RoomReadingProgressDataSource(progressDao);
  }
}
