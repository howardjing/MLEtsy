//
//  DataManager.h
//  MLEtsy
//
//  Created by Shirmung Bielefeld on 12/6/11.
//  Copyright 2011 NYU. All rights reserved.
//

#import <Foundation/Foundation.h>

@class Key;

@interface DataManager : NSObject
{
    Key *newKey;
    
    NSMutableArray *usedRandomNumbers;
    NSMutableDictionary *allRandomUserIDs;
}

@property (nonatomic, retain) Key *newKey;

@property (nonatomic, retain) NSMutableArray *usedRandomNumbers;
@property (nonatomic, retain) NSMutableDictionary *allRandomUserIDs;

- (int)obtainRandomNumber:(int)length;

- (void)obtainRandomUsers;
- (void)obtainRandomUsersFavorites;

- (void)printData:(NSMutableDictionary *)dictionaryOfArrays;

@end
