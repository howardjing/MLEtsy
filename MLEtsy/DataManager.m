//
//  DataManager.m
//  MLEtsy
//
//  Created by Shirmung Bielefeld on 12/6/11.
//  Copyright 2011 NYU. All rights reserved.
//

#import "DataManager.h"
#import "SBJson.h"
#import "Key.h"
#include <stdlib.h>

@implementation DataManager

@synthesize newKey;
@synthesize usedRandomNumbers;
@synthesize randomUsersIDs, randomUsersFavorites;

- (id)init
{
    self = [super init];
    
    if (self) 
    {
        // Initialization code here.
        newKey = [[Key alloc] init];
        
        usedRandomNumbers = [[NSMutableArray alloc] init];
        
        randomUsersIDs = [[NSMutableArray alloc] init];
        randomUsersFavorites = [[NSMutableDictionary alloc] init];
        
        [self setUpRandomUsersData];
        [self printData:randomUsersFavorites];
    }
    
    return self;
}

- (void)dealloc
{
    [newKey release];
    
    [usedRandomNumbers release];
    
    [randomUsersIDs release];
    [randomUsersFavorites release];
    
    [super dealloc];
}

// ~~~~~~~~~~~~~~~~~~~~FOR USE BY EVERY PART~~~~~~~~~~~~~~~~~~~~

// generates "random number" of specific length
- (int)obtainRandomNumber:(int)length
{
    NSString *randomNumber = @"";
    
    for (int i = 0; i < length; i++) 
    {
        int randomDigit = arc4random() % 9;
        
        NSString *intString = [NSString stringWithFormat:@"%i", randomDigit];
        randomNumber = [randomNumber stringByAppendingString:intString];
    }
    
    int randomID = [randomNumber intValue];
    
    return randomID;
}

// prints like this:
// USER: 0000000
// LISTING: 00000000
// tag,
// tag,
// tag,
// ...
- (void)printData:(NSMutableDictionary *)userDictionary
{
    for (NSString *userKey in userDictionary) 
    {
        NSLog(@"USER %@: ", userKey);
        
        NSDictionary *listingDictionary = [userDictionary objectForKey:userKey];
        
        for (NSString *listingKey in listingDictionary) 
        {
            NSLog(@"LISTING %@: ", listingKey);
            
            NSArray *listingTagsArray = [listingDictionary objectForKey:listingKey];
            
            for (NSString *tag in listingTagsArray) 
            {
                NSLog(@"%@", tag);
            }
        }
    }
}

// ~~~~~~~~~~~~~~~~~~~~PART 1: RANDOM LISTINGS~~~~~~~~~~~~~~~~~~~~

// ~~~~~~~~~~~~~~~~~~~~PART 2a: RANDOM USERS~~~~~~~~~~~~~~~~~~~~

- (void)setUpRandomUsersData
{
    // getting 500 random users
    while ([randomUsersIDs count] != 500) 
    {
        int randomUserID = [self obtainRandomNumber:7];

        NSString *randomUserRequest = [NSString stringWithFormat:@"http://openapi.etsy.com/v2/users/%i/favorites/listings?api_key=%@", randomUserID, newKey.myKey];
        NSString *randomUserResult = [NSString stringWithContentsOfURL:[NSURL URLWithString:randomUserRequest] encoding:NSUTF8StringEncoding error:NULL];
        
        if ([randomUserResult isEqualToString:[NSString stringWithFormat:@"User with PK user_id = %i does not exist", randomUserID]]) {
            NSLog(@"this user does not exist");
        } else if ([randomUserResult isEqualToString:[NSString stringWithFormat:@"Permission denied"]]) {
            NSLog(@"this user has privacy settings enabled");
        } else {
            NSDictionary *randomUserResultDictionary = [randomUserResult JSONValue];
            
            // random user needs more than 200 favorites
            if ([[randomUserResultDictionary valueForKey:@"count"] intValue] > 200) {
                    BOOL present = FALSE;
                    
                    for (NSString *randomNumber in usedRandomNumbers)
                    {
                        if ([randomNumber isEqualToString:[NSString stringWithFormat:@"%i", randomUserID]]) present = TRUE;
                    }
                    
                    if (present == FALSE) {
                        [usedRandomNumbers addObject:[NSString stringWithFormat:@"%i", randomUserID]];
                        [randomUsersIDs addObject:[NSString stringWithFormat:@"%i", randomUserID]];
                    } else {
                        NSLog(@"this user already has been accounted for");
                    }
            } else {
                NSLog(@"this user does not have more than 200 favorites");
            }
        }
    } 
    
    for (NSString *randomUserID in randomUsersIDs) 
    {
        NSMutableDictionary *randomUserFavorites = [[NSMutableDictionary alloc] init];
        
        // for each of the pages
        for (int page = 1; page <= 2; page++) 
        {
            NSString *randomUserFavoritesRequest = [NSString stringWithFormat:@"http://openapi.etsy.com/v2/users/%@/favorites/listings?limit=200&page=%i&api_key=%@", randomUserID, page, newKey.myKey];
            NSString *randomUserFavoritesResult = [NSString stringWithContentsOfURL:[NSURL URLWithString:randomUserFavoritesRequest] encoding:NSUTF8StringEncoding error:NULL];
            
            NSDictionary *randomUserFavoritesResultDictionary = [randomUserFavoritesResult JSONValue];
            
            // for each of the entries
            for (int i = 0; i < [[randomUserFavoritesResultDictionary valueForKey:@"results"] count]; i++) 
            {
                // listing request
                NSString *listingRequest = [NSString stringWithFormat:@"http://openapi.etsy.com/v2/listings/%i?api_key=%@", [[[[randomUserFavoritesResultDictionary valueForKey:@"results"] valueForKey:@"listing_id"] objectAtIndex:i] intValue], newKey.myKey];
                NSString *listingResult = [NSString stringWithContentsOfURL:[NSURL URLWithString:listingRequest] encoding:NSUTF8StringEncoding error:NULL];
                
                NSDictionary *listingResultDictionary = [listingResult JSONValue];
                
                // check if listing is active or sold out (ie: this means tags)
                if ([[[[listingResultDictionary valueForKey:@"results"] valueForKey:@"state"] objectAtIndex:0] isEqualToString:@"sold_out"] || [[[[listingResultDictionary valueForKey:@"results"] valueForKey:@"state"] objectAtIndex:0] isEqualToString:@"active"]) 
                {
                    NSArray *listingTags = [[[listingResultDictionary valueForKey:@"results"] valueForKey:@"tags"] objectAtIndex:0];
                    
                    [randomUserFavorites setObject:listingTags forKey:[[[randomUserFavoritesResultDictionary valueForKey:@"results"] valueForKey:@"listing_id"] objectAtIndex:i]];
                }
            }
        }
        
        // dictionary of array holding dictionaries
        [randomUsersFavorites setObject:randomUserFavorites forKey:randomUserID];
    }
}

// ~~~~~~~~~~~~~~~~~~~~PART 2b: NOT-SO-RANDOM USERS~~~~~~~~~~~~~~~~~~~~

@end
