package com.neepan.boockai.core.data.json;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class BookJsonParser_Factory implements Factory<BookJsonParser> {
  private final Provider<Context> contextProvider;

  private BookJsonParser_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public BookJsonParser get() {
    return newInstance(contextProvider.get());
  }

  public static BookJsonParser_Factory create(Provider<Context> contextProvider) {
    return new BookJsonParser_Factory(contextProvider);
  }

  public static BookJsonParser newInstance(Context context) {
    return new BookJsonParser(context);
  }
}
