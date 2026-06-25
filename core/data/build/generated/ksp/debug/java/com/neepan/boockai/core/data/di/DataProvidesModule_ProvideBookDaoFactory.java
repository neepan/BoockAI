package com.neepan.boockai.core.data.di;

import com.neepan.boockai.core.data.local.BoockDatabase;
import com.neepan.boockai.core.data.local.BookDao;
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
public final class DataProvidesModule_ProvideBookDaoFactory implements Factory<BookDao> {
  private final Provider<BoockDatabase> databaseProvider;

  private DataProvidesModule_ProvideBookDaoFactory(Provider<BoockDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public BookDao get() {
    return provideBookDao(databaseProvider.get());
  }

  public static DataProvidesModule_ProvideBookDaoFactory create(
      Provider<BoockDatabase> databaseProvider) {
    return new DataProvidesModule_ProvideBookDaoFactory(databaseProvider);
  }

  public static BookDao provideBookDao(BoockDatabase database) {
    return Preconditions.checkNotNullFromProvides(DataProvidesModule.INSTANCE.provideBookDao(database));
  }
}
