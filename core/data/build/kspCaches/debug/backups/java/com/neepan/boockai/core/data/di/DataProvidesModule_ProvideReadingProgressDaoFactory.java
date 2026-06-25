package com.neepan.boockai.core.data.di;

import com.neepan.boockai.core.data.local.BoockDatabase;
import com.neepan.boockai.core.data.local.ReadingProgressDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
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
public final class DataProvidesModule_ProvideReadingProgressDaoFactory implements Factory<ReadingProgressDao> {
  private final Provider<BoockDatabase> databaseProvider;

  private DataProvidesModule_ProvideReadingProgressDaoFactory(
      Provider<BoockDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public ReadingProgressDao get() {
    return provideReadingProgressDao(databaseProvider.get());
  }

  public static DataProvidesModule_ProvideReadingProgressDaoFactory create(
      Provider<BoockDatabase> databaseProvider) {
    return new DataProvidesModule_ProvideReadingProgressDaoFactory(databaseProvider);
  }

  public static ReadingProgressDao provideReadingProgressDao(BoockDatabase database) {
    return Preconditions.checkNotNullFromProvides(DataProvidesModule.INSTANCE.provideReadingProgressDao(database));
  }
}
