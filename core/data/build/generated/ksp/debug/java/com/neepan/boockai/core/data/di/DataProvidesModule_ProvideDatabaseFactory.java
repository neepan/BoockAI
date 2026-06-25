package com.neepan.boockai.core.data.di;

import android.content.Context;
import com.neepan.boockai.core.data.local.BoockDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class DataProvidesModule_ProvideDatabaseFactory implements Factory<BoockDatabase> {
  private final Provider<Context> contextProvider;

  private DataProvidesModule_ProvideDatabaseFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public BoockDatabase get() {
    return provideDatabase(contextProvider.get());
  }

  public static DataProvidesModule_ProvideDatabaseFactory create(
      Provider<Context> contextProvider) {
    return new DataProvidesModule_ProvideDatabaseFactory(contextProvider);
  }

  public static BoockDatabase provideDatabase(Context context) {
    return Preconditions.checkNotNullFromProvides(DataProvidesModule.INSTANCE.provideDatabase(context));
  }
}
