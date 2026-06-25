package com.neepan.boockai.core.datastore;

import androidx.datastore.core.DataStore;
import androidx.datastore.preferences.core.Preferences;
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
public final class DataStoreReaderPreferences_Factory implements Factory<DataStoreReaderPreferences> {
  private final Provider<DataStore<Preferences>> dataStoreProvider;

  private DataStoreReaderPreferences_Factory(Provider<DataStore<Preferences>> dataStoreProvider) {
    this.dataStoreProvider = dataStoreProvider;
  }

  @Override
  public DataStoreReaderPreferences get() {
    return newInstance(dataStoreProvider.get());
  }

  public static DataStoreReaderPreferences_Factory create(
      Provider<DataStore<Preferences>> dataStoreProvider) {
    return new DataStoreReaderPreferences_Factory(dataStoreProvider);
  }

  public static DataStoreReaderPreferences newInstance(DataStore<Preferences> dataStore) {
    return new DataStoreReaderPreferences(dataStore);
  }
}
