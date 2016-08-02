# Some common iterators for Java

[![Build Status](https://travis-ci.org/markenwerk/java-commons-iterators.svg?branch=master)](https://travis-ci.org/markenwerk/java-commons-iterators)
[![Coverage Status](https://coveralls.io/repos/markenwerk/java-commons-iterators/badge.svg?branch=master&service=github)](https://coveralls.io/github/markenwerk/java-commons-iterators?branch=master)
[![Dependency Status](https://www.versioneye.com/user/projects/564dd82a771e41003f000001/badge.svg)](https://www.versioneye.com/user/projects/564dd82a771e41003f000001)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/net.markenwerk/commons-iterators/badge.svg)](https://maven-badges.herokuapp.com/maven-central/net.markenwerk/commons-iterators)
[![Java SE 6](https://img.shields.io/badge/java-SE_6-brightgreen.svg)](http://docs.oracle.com/javase/6/docs/api/)
[![MIT License](https://img.shields.io/badge/license-MIT-brightgreen.svg)](https://github.com/markenwerk/java-commons-iterators/blob/master/LICENSE)

## Overview

This is a collection of companion iterators for [some commonly useful `Iterable` implementations](https://github.com/markenwerk/java-commons-iterables) for the following use cases:

- [Filtering and Converting](#filtering-and-converting)
- [Combining multiple `Iterators`](#combining-multiple-iterators)
- [Inserting affix values](#inserting-affix-values)
- [Looking ahead](#looking-ahead)
- [Handling of `null` values](#handling-of-null-values)
- [Handling of `remove()` operation](#handling-of-remove-operation)
- [Iterating over single values](#iterating-over-single-values)
- [Iterating over multiple values](#iterating-over-multiple-values)
- [Iterating over primitive arrays](#iterating-over-non-primitive-arrays)
- [Iterating over non-primitive arrays](#iterating-over-primitive-arrays)
- [Iterating over other objects](#iterating-over-other-objects)
- [Iterating infinitely](#iterating-infinitely)
- [Keeping track of iteration index](#keeping-track-of-iteration-index)
- [Counting up and down](#counting-up-and-down)

This library is hosted in the [Maven Central Repository](https://maven-badges.herokuapp.com/maven-central/net.markenwerk/commons-iterators). You can use it with the following coordinates:

```xml
<dependency>
	<groupId>net.markenwerk</groupId>
	<artifactId>commons-iterators</artifactId>
	<version>2.2.1</version>
</dependency>
```

Consult the [usage description](#usage) and [Javadoc](https://markenwerk.github.io/java-commons-iterators/index.html) for further information.

## Usage

### Filtering and Converting

This library provides the generic [`FilteredIterator`][FilteredIterator] that takes an existing [`Iterator`][Iterator] and a [`Predicate`][Predicate] and filters out all values yielded by the given [`Iterator`][Iterator] that don't satisfy the given [`Predicate`][Predicate].

```java
Iterator<Foo> iterator = ...

// yields every foo from from iterator that matches the condition
Iterator<Foo> FilteredIterator = new FilteredIterator<>(iterator, new Predicate<Foo>(){

	@Override
	public boolean test(Object object) throws PredicateException {
		return ...; // some condition
	}

});
```

This library provides the generic [`ConvertingIterator`][ConvertingIterator] that takes an existing [`Iterator`][Iterator] and a [`Converter`][Converter] and converts all values yielded by the given [`Iterator`][Iterator] and yields the converted values. 

```java
Iterator<Foo> iterator = ...

// yields a bar for every foo from from iterator
Iterator<Bar> convertingIterator = new ConvertingIterator<>(iterator, new Converter<Foo, Bar>(){

	@Override
	public Bar convert(Foo foo) throws ConverterException {
		return ...; // some conversion
	}

});
```

### Combining multiple `Iterators`

This library provides the generic [`CombinedIterator`][CombinedIterator] that takes multiple existing [`Iterators`][Iterator] and combines them into a single [`Iterator`][Iterator] that yields all values of all given [`Iterators`][Iterator].

It can be constructed from an array or an arbitrary amount of known [`Iterators`][Iterator].

```java
Iterator<Foo> iterator1 = ...
Iterator<Foo> iterator2 = ...

// yields every foo from every iterator1 and iterator2
Iterator<Foo> combinedIterator = new CombinedIterator<>(iterator1, iterator2); 
```

It can be constructed from an [`Iterable`][Iterable] of [`Iterators`][Iterator] (i.e. a [`List`][List]).

```java
List<Iterator<Foo>>[] iterators = ...

// yields every foo from every iterator from iterators
Iterator<Foo> combinedIterator = new CombinedIterator<>(iterators); 
```

### Inserting affix values

This library provides the generic [`PrefixedIterator`][PrefixedIterator] that takes an existing [`Iterator`][Iterator] and yields given prefix values before every value yielded by the given [`Iterator`][Iterator].

```java
Foo prefix = ...
Iterator<Foo> iterator = ...

// yields prefix and than every foo from iterator
Iterator<Foo> prefixedIterator = new PrefixedIterator<>(iterator, prefix);
```

This library provides the generic [`InfixedIterator`][InfixedIterator] that takes an existing [`Iterator`][Iterator] and yields given infix values between every value yielded by the given [`Iterator`][Iterator].

```java
Foo infix = ...
Iterator<Foo> iterator = ...

// yields every foo from iterator, interspersed with the infix
Iterator<Foo> infixedIterator = new InfixedIterator<>(iterator, infix);
```

This library provides the generic [`SuffixedIterator`][SuffixedIterator] that takes an existing [`Iterator`][Iterator] and yields given suffix values after every value yielded by the given [`Iterator`][Iterator].

```java
Foo suffix = ...
Iterator<Foo> iterator = ...

// yields every foo from iterator and than prefix
Iterator<Foo> suffixedIterator = new SuffixedIterator<>(iterator, suffix);
```

### Looking ahead

This library provides the generic [`LookAheadIterator`][LookAheadIterator] that takes an existing [`Iterator`][Iterator] and yield every value yielded by the given [`Iterator`][Iterator] wrapped in a [`LookAhead`][LookAhead] that also contains the next value. This allows to peak into the future, while iterating through the given [`Iterator`][Iterator].

```java
Iterator<Foo> iterator = ...

// yields a look ahead for every foo from iterator
Iterator<LookAhead<Foo>> lookAheadIterator = new LookAheadIterator<>(iterator);
```

A [`LookAheadIterator`][LookAheadIterator] can be used to easily perform actions between every element yielded by the original [`Iterator`][Iterator].

```java
while (lookAheadIterator.hasNext()) {
    LookAhead<Foo> lookAhead = lookAheadIterator.next();
    // returns the current foo
    Foo currentFoo = lookAhead.get() 
    doForEveryFoo(currentFoo);
    // returns whether the look ahead has a next value
    if (lookAhead.hasNext()) {  
        // returns the next foo without modifying lookAheadIterator
        Foo nextFoo = lookAhead.getNext();
        doBetweenFoos(currentFoo, nextFoo);
    }  
}
```

### Handling of `null` values

For situations where it is necessary to provide an [`Iterator`][Iterator], but no meaningful [`Iterator`][Iterator] is available, it might be useful to create an [`EmptyIterator`][EmptyIterator]. An [`EmptyIterator`][EmptyIterator] doesn't yield any values, but fulfills the [`Iterator`][Iterator] contract.

```java
// yields nothing
Iterator<Foo> emptyIterator = new EmptyIterator<>();
```

For situations where some [`Iterator`][Iterator] is obtained and passed along, it might be useful to wrap the obtained [`Iterator`][Iterator] in a [`NullSaveIterator`][NullSaveIterator]. A [`NullSaveIterator`][NullSaveIterator] always fulfills the [`Iterator`][Iterator] contract, even if it is constructed from a `null` [`Iterator`][Iterator].

```java
// an iterator that may be null
Iterator<Foo> iterator = ...

// yields every foo from iterator of nothing, if iterator is null
Iterator<Foo> nullSaveIterator = new NullSaveIterator<>(iterator); 
```

For situations where some [`Iterator`][Iterator] is obtained, it might be useful to wrap the obtained [`Iterator`][Iterator] in a [`NullFreeIterator`][NullFreeIterator]. A [`NullFreeIterator`][NullFreeIterator] filters out all `null` values yielded by the obtained [`Iterator`][Iterator].

```java
Iterator<Foo> iterator = ...

// yields every non-null foo from iterator
Iterator<Foo> nullFreeIterator = new NullFreeIterator<>(iterator);
```

### Handling of `remove()` operation

This library provides the [`ProtectedIterator`][ProtectedIterator] interface. A [`ProtectedIterator`][ProtectedIterator] is an [`Iterator`][Iterator] that must throw a [`UnsupportedOperationException`][UnsupportedOperationException], if `remove()` is called.

This library provides the [`ProtectingIterator`][ProtectingIterator] that takes an existing [`Iterator`][Iterator] and yields all values yielded by the given [`Iterator`][Iterator], but throws a [`UnsupportedoperationException`][UnsupportedoperationException], if `remove()` is called.

```java
Iterator<Foo> iterator = ...

// yields every foo from iterator, but doesn't allow removing
ProtectingIterator<Foo> protectingIterator = new ProtectingIterator<>(iterator); 
```

This library provides the [`RemoveHandlerIterator`][RemoveHandlerIterator] that takes an existing [`Iterator`][Iterator] and a [`Handler`][Handler] and yields all values yielded by the given [`Iterator`][Iterator], but calls the given handler with the last yielded value, instead of calling `remove()` on the given [`Iterator`][Iterator].

```java
Iterator<Foo> iterator = ...

// yields every foo from iterator, but intercepts removing
Iterator<Foo> removeHandlerIterator = new RemoveHandlerIterator<>(iterator, new Handler<Foo>(){

	@Override
	public void handle(Foo foo) {
		System.out.println(foo + " has been removed"); // some handling
	}
	
}); 
```

### Iterating over single values

This library provides the [`ObjectIterator`][ObjectIterator] and [`OptionalIterator`][OptionalIterator] that take and yield an existing object. An [`ObjectIterator`][ObjectIterator] always yields the given object, whereas an [`OptionalIterator`][OptionalIterator] only yields the given object, if it isn't `null`. 

```java
Foo foo = ...

// yields foo, even if foo is null
Iterator<Foo> objectIterator = new ObjectIterator<>(foo); 

// yields foo or nothing, if foo is null
Iterator<Foo> optionalIterator = new OptionalIterator<>(foo); 
```

### Iterating over multiple values

This library provides the generic [`PairIterator`][PairIterator] that takes an existing [`Pair`][Pair] and yields both elements of the given [`Pair`][Pair]:

```java
Pair<Foo> pair = ...

// yields both foos from pair
Iterator<Foo> pairIterator = new PairIterator<>(pair); 
```

This library provides the generic [`TupleIterator`][TupleIterator] that takes an existing [`Tuple`][Tuple] with elements of a similar type and yields both elements of the given [`Tuple`][Tuple]:

```java
Tuple<Foo, Foo> tuple = ...

// yields both foos from tuple
Iterator<Foo> tupleIterator = new TupleIterator<>(tuple); 
```

This library provides the generic [`TripleIterator`][TripleIterator] that takes an existing [`Triple`][Triple] with elements of a similar type and yields both elements of the given [`Triple`][Triple]:

```java
Triple<Foo, Foo, Foo> triple = ...

// yields all foos from triple
Iterator<Foo> tripleIterator = new TripleIterator<>(triple); 
```

### Iterating over primitive arrays

This library provides the following [`Iterators`][Iterator] that take existing primitive arrays and yield all elements of the given array:

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

// yields every boolean from booleans
Iterator<Boolean> arraytIterator = new BooleanArrayIterator(booleans);
```

### Iterating over non-primitive arrays

This library provides the generic [`ArrayIterator`][ArrayIterator] that takes an existing array and yields all elements of the given array:

```java
Foo[] foos = ...

// yields every foo from foos
Iterator<Foo> arrayIterator = new ArrayIterator<>(foos); 
```

### Iterating over other objects

This library provides the generic [`EnumerationIterator`][EnumerationIterator] that takes an existing [`Enumeration`][Enumeration] and yields every object yielded by the given [`Enumeration`][Enumeration].

```java
Enumeration<Foo> enumeration = ...

// yields every foo from enumeration
Iterator<Foo> enumerationIterator = new EnumerationIterator<>(enumeration); 
```

This library provides the [`NodeListIterator`][NodeListIterator] that takes an existing [`NodeList`][NodeList] and yields every [`Node`][Node] yielded by the given [`NodeList`][NodeList].

```java
NodeList nodeList = ...

// yields every node from nodeList
Iterator<Node> nodeListIterator = new NodeListIterator(nodeList); 
```

This library provides the [`StringTokenizerIterator`][StringTokenizerIterator] that takes an existing [`StringTokenizer`][StringTokenizer] and yields every string yielded by the given [`StringTokenizer`][StringTokenizer].

```java
StringTokenizer tokenizer = ...

// yields every string from tokenizer
Iterator<String> tokenizerIterator = new StringTokenizerIterator(tokenizer); 
```

### Iterating infinitely

This library provides the [`InfiniteIterator`][InfiniteIterator] that takes a [`Provider`][Provider] and infinitely yields provided values.

```java
Provider<Foo> provider = ...

// yields provided foos forever
Iterator<Foo> infiniteIterator = new InfiniteIterator(provider); 
```

### Keeping track of iteration index

This library provides the generic [`IndexedIterator`][IndexedIterator] that takes an existing [`Iterator`][Iterator] and yield every value yielded by the given [`Iterator`][Iterator] wrapped in an [`Entry`][Entry] whose key is the iteration index of the yielded element.

```java
Iterator<Foo> iterator = ...

// yields an entry for every foo from iterator with
Iterator<Entry<Integer, Foo>> indexedIterator = new IndexedIterator<>(iterator);
```

### Counting up and down

This library provides the [`CountDownIterator`][CountDownIterator] and [`CountUpIterator`][CountUpIterator] that take two integer values as bounds and yield every integer value between the given bounds, moving downwards or upwards respectively.

```java
// yields 10, 9, ..., 1, 0
Iterator<Integer> countDownIterator = new CountDownIterator(10, 0);

// yields 0, 1, ..., 9, 10 
Iterator<Integer> countUpIterator = new CountUpIterator(0, 10); 
```


[ArrayIterator]: https://markenwerk.github.io/java-commons-iterators/index.html?net/markenwerk/commons/iterators/ArrayIterator.html
[BooleanArrayIterator]: https://markenwerk.github.io/java-commons-iterators/index.html?net/markenwerk/commons/iterators/BooleanArrayIterator.html
[ByteArrayIterator]: https://markenwerk.github.io/java-commons-iterators/index.html?net/markenwerk/commons/iterators/ByteArrayIterator.html
[CharacterArrayIterator]: https://markenwerk.github.io/java-commons-iterators/index.html?net/markenwerk/commons/iterators/CharacterArrayIterator.html
[CombinedIterator]: https://markenwerk.github.io/java-commons-iterators/index.html?net/markenwerk/commons/iterators/CombinedIterator.html
[ConvertingIterator]: https://markenwerk.github.io/java-commons-iterators/index.html?net/markenwerk/commons/iterators/ConvertingIterator.html
[CountDownIterator]: https://markenwerk.github.io/java-commons-iterators/index.html?net/markenwerk/commons/iterators/CountDownIterator.html
[CountUpIterator]: https://markenwerk.github.io/java-commons-iterators/index.html?net/markenwerk/commons/iterators/CountUpIterator.html
[DoubleArrayIterator]: https://markenwerk.github.io/java-commons-iterators/index.html?net/markenwerk/commons/iterators/DoubleArrayIterator.html
[EmptyIterator]: https://markenwerk.github.io/java-commons-iterators/index.html?net/markenwerk/commons/iterators/EmptyIterator.html
[EnumerationIterator]: https://markenwerk.github.io/java-commons-iterators/index.html?net/markenwerk/commons/iterators/EnumerationIterator.html
[FilteredIterator]: https://markenwerk.github.io/java-commons-iterators/index.html?net/markenwerk/commons/iterators/FilteredIterator.html
[FloatArrayIterator]: https://markenwerk.github.io/java-commons-iterators/index.html?net/markenwerk/commons/iterators/FloatArrayIterator.html
[IntegerArrayIterator]: https://markenwerk.github.io/java-commons-iterators/index.html?net/markenwerk/commons/iterators/IntegerArrayIterator.html
[IndexedIterator]: https://markenwerk.github.io/java-commons-iterators/index.html?net/markenwerk/commons/iterators/IndexedIterator.html
[InfiniteIterator]: https://markenwerk.github.io/java-commons-iterators/index.html?net/markenwerk/commons/iterators/InfiniteIterator.html
[InfixedIterator]: https://markenwerk.github.io/java-commons-iterators/index.html?net/markenwerk/commons/iterators/InfixedIterator.html
[LongArrayIterator]: https://markenwerk.github.io/java-commons-iterators/index.html?net/markenwerk/commons/iterators/LongArrayIterator.html
[LookAhead]: https://markenwerk.github.io/java-commons-iterators/index.html?net/markenwerk/commons/iterators/LookAhead.html
[LookAheadIterator]: https://markenwerk.github.io/java-commons-iterators/index.html?net/markenwerk/commons/iterators/LookAheadIterator.html
[NullFreeIterator]: https://markenwerk.github.io/java-commons-iterators/index.html?net/markenwerk/commons/iterators/NullFreeIterator.html
[NullSaveIterator]: https://markenwerk.github.io/java-commons-iterators/index.html?net/markenwerk/commons/iterators/NullSaveIterator.html
[ObjectIterator]: https://markenwerk.github.io/java-commons-iterators/index.html?net/markenwerk/commons/iterators/ObjectIterator.html
[OptionalIterator]: https://markenwerk.github.io/java-commons-iterators/index.html?net/markenwerk/commons/iterators/OptionalIterator.html
[PairIterator]: https://markenwerk.github.io/java-commons-iterators/index.html?net/markenwerk/commons/iterators/PairIterator.html
[PrefixedIterator]: https://markenwerk.github.io/java-commons-iterators/index.html?net/markenwerk/commons/iterators/PrefixedIterator.html
[ProtectedIterator]: https://markenwerk.github.io/java-commons-iterators/index.html?net/markenwerk/commons/iterators/ProtectedIterator.html
[ProtectingIterator]: https://markenwerk.github.io/java-commons-iterators/index.html?net/markenwerk/commons/iterators/ProtectingIterator.html
[NodeListIterator]: https://markenwerk.github.io/java-commons-iterators/index.html?net/markenwerk/commons/iterators/NodeListIterator.html
[RemoveHandlerIterator]: https://markenwerk.github.io/java-commons-iterators/index.html?net/markenwerk/commons/iterators/RemoveHandlerIterator.html
[ShortArrayIterator]: https://markenwerk.github.io/java-commons-iterators/index.html?net/markenwerk/commons/iterators/ShortArrayIterator.html
[StringTokenizerIterator]: https://markenwerk.github.io/java-commons-iterators/index.html?net/markenwerk/commons/iterators/StringTokenizerIterator.html
[SuffixedIterator]: https://markenwerk.github.io/java-commons-iterators/index.html?net/markenwerk/commons/iterators/SuffixedIterator.html
[TripleIterator]: https://markenwerk.github.io/java-commons-iterators/index.html?net/markenwerk/commons/iterators/TripleIterator.html
[TupleIterator]: https://markenwerk.github.io/java-commons-iterators/index.html?net/markenwerk/commons/iterators/TupleIterator.html

[Converter]: https://markenwerk.github.io/java-commons-interfaces/index.html?net/markenwerk/commons/interfaces/Converter.html
[Handler]: https://markenwerk.github.io/java-commons-interfaces/index.html?net/markenwerk/commons/interfaces/Handler.html
[Predicate]: https://markenwerk.github.io/java-commons-interfaces/index.html?net/markenwerk/commons/interfaces/Predicate.html
[Provider]: https://markenwerk.github.io/java-commons-interfaces/index.html?net/markenwerk/commons/interfaces/Provider.html

[Entry]: https://markenwerk.github.io/java-commons-datastructures/index.html?net/markenwerk/commons/datastructures/Entry.html
[Pair]: https://markenwerk.github.io/java-commons-datastructures/index.html?net/markenwerk/commons/datastructures/Pair.html
[Triple]: https://markenwerk.github.io/java-commons-datastructures/index.html?net/markenwerk/commons/datastructures/Triple.html
[Tuple]: https://markenwerk.github.io/java-commons-datastructures/index.html?net/markenwerk/commons/datastructures/Tuple.html

[Enumeration]: https://docs.oracle.com/javase/8/docs/api/index.html?java/util/Enumeration.html
[Iterable]: http://docs.oracle.com/javase/8/docs/api/index.html?java/lang/Iterable.html
[Iterator]: http://docs.oracle.com/javase/8/docs/api/index.html?java/util/Iterator.html
[List]: http://docs.oracle.com/javase/8/docs/api/index.html?java/util/List.html
[Node]: https://docs.oracle.com/javase/8/docs/api/index.html?org/w3c/dom/Node.html
[NodeList]: https://docs.oracle.com/javase/8/docs/api/index.html?org/w3c/dom/NodeList.html
[StringTokenizer]: https://docs.oracle.com/javase/8/docs/api/index.html?java/util/StringTokenizer.html
[UnsupportedOperationException]: https://docs.oracle.com/javase/8/docs/api/index.html?java/lang/UnsupportedOperationException.html

