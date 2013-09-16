Object subclass: UndefinedObject [
       <instanceVariableNames: ''>
       <classVariableNames: ''>
    
    printOn: aStream [
        aStream nextPutAll: 'nil'
    ] 
    
    ifNil: aBlock [
        ^aBlock value
    ]
    
    ifNotNil: aBlock [
        ^self
    ]
    
    isNil [
        ^true
    ]
    
    notNil [
        ^false
    ] 
]