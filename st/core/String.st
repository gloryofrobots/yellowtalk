"
Childtalk is 
    Copyright (c) 2013 Alex Birulia

Childtalk standard library is based on the Smalltalk YX which is 
    Copyright (c) 2007-2008 Luca Bruno

Original license of Smalltalk YX may be found in the LICENSE file.
"

ByteArray subclass: String [

    (+) new: anIndex [
        "Add 1 to anIndex to make room for NULL character"
        ^ self basicNew: anIndex + 1
    ]
    
    (+) new [
        "Add 1 to anIndex to make room for NULL character"
        ^ self basicNew: 16.
    ]
    
    at: anIndex [
        ^self basicAt: anIndex
    ]
    
    at: anIndex put: aCharacter [
        
        ^self basicAt: anIndex put: aCharacter
    ]
    
    basicAt: anIndex [
        anIndex > self basicSize
            ifTrue: [ self error: 'Index out of bounds' ].
        ^Character value: (self byteAt: anIndex)
    ]
    
    basicAt: anIndex put: aCharacter [
        "thisContext showTraceBack."
        anIndex > self basicSize
            ifTrue: [ self error: 'Index out of bounds' ].
        (aCharacter isKindOf: Character)
    	ifTrue: [ self byteAt: anIndex put: aCharacter asciiValue. ^aCharacter ].
        (aCharacter isKindOf: SmallInteger)
        ifTrue: [self byteAt: anIndex put: aCharacter.  ^aCharacter ].
        
        self error: 'A character or SmallInteger required got '+ aCharacter printString
    ]
    
    printOn: aStream [
        aStream
            nextPutAll: self.
            
    ]
    
    storeOn: aStream [
        aStream
            nextPutAll: self.
    ]
    
    hash [
        <primitive: 'String_hash'>
    	self primitiveFailed
    ]
    
    size [
        "The size is less by 1 because of the NULL character"
        ^self basicSize
    ]
    
    
    subStrings: separators [
        | stream index size subindex |
        stream := WriteStream on: Array new.
        size := separators size.
        index := 1.
        [ index <= self size ]
    	whileTrue: [
    	    subindex := self indexOfSubCollection: separators startingAt: index.
    	    subindex = 0
    		ifTrue: [ stream nextPut: (self copyFrom: index to: self size).
    			  ^stream contents ].
    	    stream nextPut: (self copyFrom: index to: subindex - 1).
    	    index := subindex + size ].
        ^stream contents
    ]
    
    asSymbol [
        <primitive: 'String_asSymbol'>
    	self primitiveFailed
    ]
    
    asString [
        ^self
    ]
    
    asUppercase [
        ^self collect: [ :ea | ea asUppercase ]
    ]
    
    asLowercase [
        ^self collect: [ :ea | ea asLowercase ]
    ]
    
    asNumber [
        ^Number readFrom: (ReadStream on: self)
    ]
    
    isCapitalized [
        ^self first isUppercase
    ]
    
    isString [
        ^true
    ]
    
    
    sameAs: aCollection [
        self size == aCollection size
    	ifFalse: [ ^false ].
    
        self with: aCollection do: [ :a :b |
    	a = b
    	    ifFalse: [ ^false ] ].
    
        ^true
    ]
    
    = aString [
         self hash = aString hash
         ifTrue: [ ^true ]
    	 ifFalse: [ ^false ].
    ]
    
    < aString [
        ^(self compare: aString) < 0
    ]
    
    > aString [
        ^(self compare: aString) > 0
    ]
    
    <= aString [
        ^(self compare: aString) <= 0
    ]
    
    >= aString [
        ^(self compare: aString) >= 0
    ]
    
    compare: aString [
        1 to: (self size min: aString size) do: [ :i |
    	| n1 n2 |
    	n1 := (self at: i) asciiValue.
    	n2 := (aString at: i) asciiValue.
    	n1 = n2 ifFalse: [ ^n1 - n2 ] ].
    
        ^self size - aString size
    ]
]