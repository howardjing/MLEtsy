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
    
    NSMutableDictionary *ourUserFavs;
    
    NSMutableDictionary *randomListings;
    
    NSMutableArray *randomUsersIDs;
    NSMutableDictionary *randomUsersFavs;
}

@property (nonatomic, retain) Key *newKey;

@property (nonatomic, retain) NSMutableArray *usedRandomNumbers;

@property (nonatomic, retain) NSMutableDictionary *ourUserFavs;

@property (nonatomic, retain) NSMutableDictionary *randomListings;

@property (nonatomic, retain) NSMutableArray *randomUsersIDs;
@property (nonatomic, retain) NSMutableDictionary *randomUsersFavs;

- (int)obtainRandomNumber:(int)length;
- (void)resetUsedRandomNumbers;
- (void)printData:(NSMutableDictionary *)userDictionary;

- (void)setUpOurUserData;
- (void)setUpRandomListingsData;
- (void)setUpRandomUsersData;

@end
