# Some common iterators for Java

[![Build Status](https://travis-ci.org/markenwerk/java-commons-iterators.svg?branch=master)](https://travis-ci.org/markenwerk/java-commons-iterators)
[![Coverage Status](https://coveralls.io/repos/markenwerk/java-commons-iterators/badge.svg?branch=master&service=github)](https://coveralls.io/github/markenwerk/java-commons-iterators?branch=master)
[![Dependency Status](https://www.versioneye.com/user/projects/56323f1636d0ab0016001bc4/badge.svg)](https://www.versioneye.com/user/projects/56323f1636d0ab0016001bc4)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/net.markenwerk/commons-iterators/badge.svg)](https://maven-badges.herokuapp.com/maven-central/net.markenwerk/commons-iterators)

This is a collection of some commonly useful [`Iterator`][Iterator] implementations.

## Overview

This library is hosted in the [Maven Central Repositoy](http://search.maven.org/#artifactdetails|net.markenwerk|java-commons-iterators|1.1.0|jar). You can use it with the following coordinates:

```xml
<dependency>
	<groupId>net.markenwerk</groupId>
	<artifactId>java-commons-iterators</artifactId>
	<version>1.1.0</version>
</dependency>
```

Consult the [usage description](#usage) and [Javadoc](http://markenwerk.github.io/java-commons-iterators/javadoc/1.1.0/index.html) for further information.

## Usage

### Filtering and Converting

This library provides the generic [`FilteringIterator`][FilteringIterator] that takes an existing [`Iterator`][Iterator] and a [`Predicate`][Predicate] and filters out all values yielded by the given [`Iterator`][Iterator] that don't satisfy the given [`Predicate`][Predicate].

```java
Iterator<Foo> iterator = ...
Iterator<Foo> filteringIterator = new FilteringIterator<Foo>(iterator, new Predicate<Foo>(){

	@Override
	public boolean test(Object object) throws PredicateException {
		return ...; // some condition
	}

});
```

This library provides the generic [`ConvertingIterator`][ConvertingIterator] that takes an existing [`Iterator`][Iterator] and a [`Converter`][Converter] and converts all values yielded by the given [`Iterator`][Iterator] and yields the converted values. 

```java
Iterator<Foo> iterator = ...
Iterator<Bar> convertingIterator = new ConvertingIterator<Foo, Bar>(iterator, new Converter<Foo, Bar>(){

	@Override
	public Bar convert(Foo foo) throws ConverterException {
		return ...; // some conversion
	}

});
```

### Combining multiple [`Iterators`][Iterator]

This library provides the generic [`CombinedIterator`][CombinedIterator] that takes multiple existing [`Iterators`][Iterator] and combines them into a single [`Iterator`][Iterator] that yields all values of all given [`Iterators`][Iterator].

It can be constructed from an array or an arbitrary amount of known [`Iterators`][Iterator].

```java
Iterator<Foo> iterator1 = ...
Iterator<Foo> iterator2 = ...
Iterator<Foo> combinedIterator = new CombinedIterator<Foo>(iterator1, iterator2);
```

It can be constructed from an [`Iterable`][Iterable] of [`Iterators`][Iterator] (i.e. a [`List`][List]).

```java
List<Foo>[] iterators = ...
Iterator<Foo> combinedIterator = new CombinedIterator<Foo>(iterators);
```

### Counting

This library provides the [`CountDownIterator`][CountDownIterator] and [`CountUpIterator`][CountUpIterator] that take two integer values as bounds and yield every integer value between the given bounds, going downwards or upwards respectively.

```java
Iterator<Integer> = new CountDownIterator(10, 0); // yields 10, 9, ..., 1, 0
Iterator<Integer> = new CountUpIterator(0, 10); // yields 0, 1, ..., 9, 10
```

### Handling of `null` values

For situations where it is necessary to provide an [`Iterator`][Iterator], but no meaningful [`Iterator`][Iterator] is available, it might be useful to create an [`EmptyIterator`][EmptyIterator]. An [`EmptyIterator`][EmptyIterator] doesn't yield any values, buf fulfils the [`Iterator`][Iterator] contract.

```java
Iterator<Foo> = new EmptyIterator<Foo>();
```

For situations where some [`Iterator`][Iterator] is obtained and passed along, it might be useful to wrap the obtained [`Iterator`][Iterator] in a [`NullSaveIterator`][NullSaveIterator]. A [`NullSaveIterator`][NullSaveIterator] always fulfils the [`Iterator`][Iterator] contract, even if it is constructed from a `null` [`Iterator`][Iterator].

```java
Iterator<Foo> iterator = ...
iterator = new NullSaveIterator(iterator);
```

For situations where some [`Iterator`][Iterator] is obtained, it might be useful to wrap the obtained [`Iterator`][Iterator] in a [`NullFreeIterator`][NullFreeIterator]. A [`NullFreeIterator`][NullFreeIterator] filters out all `null` values yielded by the obtained [`Iterator`][Iterator] and doesn't yiels any `null` value itself.

```java
Iterator<Foo> iterator = ...
iterator = new NullFreeIterator(iterator);
```

### Iterating over primitive Arrays

This library provides the following [`Iterators`][Iterator] that takes an existing primitive array and yields all elements of the given array:

- [`BooleanArrayIterator`][BooleanArrayIterator]
- [`ByteArrayIterator`][ByteArrayIterator]
- [`CharacterArrayIterator`][CharacterArrayIterator]
- [`DoubleArrayIterator`][DoubleArrayIterator]
- [`FloatArrayIterator`][FloatArrayIterator]
- [`IntegerArrayIterator`][IntegerArrayIterator]
- [`LongArrayIterator`][LongArrayIterator]
- [`ShortArrayIterator`][ShortArrayIterator]

```java
boolean[] booleans = ...
Iterator<Boolean> = new BooleanArrayIterator(booleans);
```

### Iterating over non-primitive Arrays

This library provides the generic [`ArrayIterator`][ArrayIterator] that takes an existing array and yields all elements of the given array:

```java
Foo[] foos = ...
Iterator<Foo> = new ArrayIterator(foos);
```


[ArrayIterator]: http://markenwerk.github.io/java-commons-iterators/javadoc/1.1.0/index.html?net/markenwerk/commons/iterators/ArrayIterator.html
[BooleanArrayIterator]: http://markenwerk.github.io/java-commons-iterators/javadoc/1.1.0/index.html?net/markenwerk/commons/iterators/BooleanArrayIterator.html
[ByteArrayIterator]: http://markenwerk.github.io/java-commons-iterators/javadoc/1.1.0/index.html?net/markenwerk/commons/iterators/ByteArrayIterator.html
[CharacterArrayIterator]: http://markenwerk.github.io/java-commons-iterators/javadoc/1.1.0/index.html?net/markenwerk/commons/iterators/CharacterArrayIterator.html
[CombinedIterator]: http://markenwerk.github.io/java-commons-iterators/javadoc/1.1.0/index.html?net/markenwerk/commons/iterators/CombinedIterator.html
[ConvertingIterator]: http://markenwerk.github.io/java-commons-iterators/javadoc/1.1.0/index.html?net/markenwerk/commons/iterators/ConvertingIterator.html
[CountDownIterator]: http://markenwerk.github.io/java-commons-iterators/javadoc/1.1.0/index.html?net/markenwerk/commons/iterators/CountDownIterator.html
[CountUpIterator]: http://markenwerk.github.io/java-commons-iterators/javadoc/1.1.0/index.html?net/markenwerk/commons/iterators/CountUpIterator.html
[DoubleArrayIterator]: http://markenwerk.github.io/java-commons-iterators/javadoc/1.1.0/index.html?net/markenwerk/commons/iterators/DoubleArrayIterator.html
[EmptyIterator]: http://markenwerk.github.io/java-commons-iterators/javadoc/1.1.0/index.html?net/markenwerk/commons/iterators/EmptyIterator.html
[FilteringIterator]: http://markenwerk.github.io/java-commons-iterators/javadoc/1.1.0/index.html?net/markenwerk/commons/iterators/FilteringIterator.html
[FloatArrayIterator]: http://markenwerk.github.io/java-commons-iterators/javadoc/1.1.0/index.html?net/markenwerk/commons/iterators/FloatArrayIterator.html
[IntegerArrayIterator]: http://markenwerk.github.io/java-commons-iterators/javadoc/1.1.0/index.html?net/markenwerk/commons/iterators/IntegerArrayIterator.html
[LongArrayIterator]: http://markenwerk.github.io/java-commons-iterators/javadoc/1.1.0/index.html?net/markenwerk/commons/iterators/LongArrayIterator.html
[NullFreeIterator]: http://markenwerk.github.io/java-commons-iterators/javadoc/1.1.0/index.html?net/markenwerk/commons/iterators/NullFreeIterator.html
[NullSaveIterator]: http://markenwerk.github.io/java-commons-iterators/javadoc/1.1.0/index.html?net/markenwerk/commons/iterators/NullSaveIterator.html
[ShortArrayIterator]: http://markenwerk.github.io/java-commons-iterators/javadoc/1.1.0/index.html?net/markenwerk/commons/iterators/ShortArrayIterator.html

[Converter]: http://markenwerk.github.io/java-commons-interfaces/javadoc/1.1.0/index.html?net/markenwerk/commons/interfaces/Converter.html
[Predicate]: http://markenwerk.github.io/java-commons-interfaces/javadoc/1.1.0/index.html?net/markenwerk/commons/interfaces/Predicate.html

[Iterable]: http://docs.oracle.com/javase/6/docs/api/index.html?java/lang/Iterable.html
[Iterator]: http://docs.oracle.com/javase/6/docs/api/index.html?java/util/Iterator.html
[List]: http://docs.oracle.com/javase/6/docs/api/index.html?java/util/List.html
