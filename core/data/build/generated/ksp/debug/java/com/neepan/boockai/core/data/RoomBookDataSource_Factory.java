package com.neepan.boockai.core.data;

import com.neepan.boockai.core.data.json.BookJsonParser;
import com.neepan.boockai.core.data.local.BookDao;
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
public final class RoomBookDataSource_Factory implements Factory<RoomBookDataSource> {
  private final Provider<BookDao> bookDaoProvider;

  private final Provider<BookJsonParser> bookJsonParserProvider;

  private RoomBookDataSource_Factory(Provider<BookDao> bookDaoProvider,
      Provider<BookJsonParser> bookJsonParserProvider) {
    this.bookDaoProvider = bookDaoProvider;
    this.bookJsonParserProvider = bookJsonParserProvider;
  }

  @Override
  public RoomBookDataSource get() {
    return newInstance(bookDaoProvider.get(), bookJsonParserProvider.get());
  }

  public static RoomBookDataSource_Factory create(Provider<BookDao> bookDaoProvider,
      Provider<BookJsonParser> bookJsonParserProvider) {
    return new RoomBookDataSource_Factory(bookDaoProvider, bookJsonParserProvider);
  }

  public static RoomBookDataSource newInstance(BookDao bookDao, BookJsonParser bookJsonParser) {
    return new RoomBookDataSource(bookDao, bookJsonParser);
  }
}
