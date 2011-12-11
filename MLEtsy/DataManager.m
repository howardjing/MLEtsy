//
//  DataManager.m
//  MLEtsy
//
//  Created by Shirmung Bielefeld on 12/6/11.
//  Copyright 2011 NYU. All rights reserved.
//
//  NOTE: UNFORTUNATELY THIS COULD BE WRITTEN MORE OOP-LY :(

#import "DataManager.h"
#import "ChosenUsersData.h"
#import "Key.h"
#import "SBJson.h"
#include <stdlib.h>

@implementation DataManager

@synthesize newKey;
@synthesize usedRandomNumbers;
@synthesize ourUserFavs;
@synthesize randomListings;
@synthesize randomUsersIDs, randomUsersFavs;
@synthesize chosenUsers, chosenUsersFavs;

- (id)init
{
    self = [super init];
    
    if (self) 
    {
        // Initialization code here.
        newKey = [[Key alloc] init];
        
        usedRandomNumbers = [[NSMutableArray alloc] init];
        
        ourUserFavs = [[NSMutableDictionary alloc] init];
        
        //[self setUpOurUserData];
        //[self printData:ourUserFavs];
        
        randomListings = [[NSMutableDictionary alloc] init];
        
        //[self setUpRandomListingsData];
        //[self printData:randomListings];
        
        randomUsersIDs = [[NSMutableArray alloc] init];
        randomUsersFavs = [[NSMutableDictionary alloc] init];
        
        //[self setUpRandomUsersData];
        //[self printData:randomUsersFavs];
    
        chosenUsers = [[ChosenUsersData alloc] init];
        chosenUsersFavs = [[NSMutableDictionary alloc] init];
        
        [self setUpChosenUsersData];
        [self printData:chosenUsersFavs];
    }
    
    return self;
}

- (void)dealloc
{
    [newKey release];
    
    [usedRandomNumbers release];
    
    [ourUserFavs release];
    
    [randomListings release];
    
    [randomUsersIDs release];
    [randomUsersFavs release];
    
    [chosenUsers release];
    [chosenUsersFavs release];
    
    [super dealloc];
}

#pragma mark - FOR USE BY EVERY PART

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

// obvious
- (void)resetUsedRandomNumbers
{
    [usedRandomNumbers removeAllObjects];
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

#pragma mark - PART 0: OUR USER

- (void)setUpOurUserData
{   
    // just to maintain printing format
    NSMutableDictionary *spaceFiller = [[NSMutableDictionary alloc] init];
    
    // for each of the pages
    for (int page = 1; page <= 44; page++) 
    {
        NSString *userFavsRequest = [NSString stringWithFormat:@"http://openapi.etsy.com/v2/users/5332839/favorites/listings?limit=4317&page=%i&api_key=%@", page, newKey.myKey];
        NSString *userFavsResult = [NSString stringWithContentsOfURL:[NSURL URLWithString:userFavsRequest] encoding:NSUTF8StringEncoding error:NULL];
        
        NSDictionary *userFavsResultJSON = [userFavsResult JSONValue];
    
        // for each of the entries
        for (int i = 0; i < [[userFavsResultJSON valueForKey:@"results"] count]; i++) 
        {
            // listing request
            NSString *listingRequest = [NSString stringWithFormat:@"http://openapi.etsy.com/v2/listings/%i?api_key=%@", [[[[userFavsResultJSON valueForKey:@"results"] valueForKey:@"listing_id"] objectAtIndex:i] intValue], newKey.myKey];
            NSString *listingResult = [NSString stringWithContentsOfURL:[NSURL URLWithString:listingRequest] encoding:NSUTF8StringEncoding error:NULL];
            
            NSDictionary *listingResultJSON = [listingResult JSONValue];
            
            // check if listing is active or sold out (ie: this means tags)
            if ([[[[listingResultJSON valueForKey:@"results"] valueForKey:@"state"] objectAtIndex:0] isEqualToString:@"sold_out"] || [[[[listingResultJSON valueForKey:@"results"] valueForKey:@"state"] objectAtIndex:0] isEqualToString:@"active"]) 
            {
                NSArray *listingTags = [[[listingResultJSON valueForKey:@"results"] valueForKey:@"tags"] objectAtIndex:0];
                
                [spaceFiller setObject:listingTags forKey:[[[userFavsResultJSON valueForKey:@"results"] valueForKey:@"listing_id"] objectAtIndex:i]];
            }
        }
    }
    
    // dictionary of array holding dictionaries
    [ourUserFavs setObject:spaceFiller forKey:[NSString stringWithFormat:@"%i", 5332839]];
}

#pragma mark - PART 1: RANDOM LISTINGS

- (void)setUpRandomListingsData
{
    [self resetUsedRandomNumbers];
    
    // just to maintain printing format
    NSMutableDictionary *spaceFiller = [[NSMutableDictionary alloc] init];

    // get 8000 random listings
    while ([randomListings count] != 8000) 
    {
        int randomListingID = [self obtainRandomNumber:7];
    
        NSString *randomListingRequest = [NSString stringWithFormat:@"http://openapi.etsy.com/v2/listings/%i?api_key=%@", randomListingID, newKey.myKey];
        NSString *randomListingResult = [NSString stringWithContentsOfURL:[NSURL URLWithString:randomListingRequest] encoding:NSUTF8StringEncoding error:NULL];
    
        if ([randomListingResult isEqualToString:[NSString stringWithFormat:@"Translation_TranslatableFinder: Listing-&gt;find(%i) does not exist", randomListingID]]) {
            NSLog(@"this listing does not exist");
        } else {
            NSDictionary *randomListingResultJSON = [randomListingResult JSONValue];
        
            // for each of the entries
            for (int i = 0; i < [[randomListingResultJSON valueForKey:@"results"] count]; i++) 
            {
                BOOL present = FALSE;
                    
                for (NSString *randomNumber in usedRandomNumbers)
                {
                    if ([randomNumber isEqualToString:[NSString stringWithFormat:@"%i", randomListingID]]) present = TRUE;
                }
            
                if (present == FALSE) {
                    NSLog(@"adding listing %i", randomListingID);
                    
                    [usedRandomNumbers addObject:[NSString stringWithFormat:@"%i", randomListingID]];
                    
                    // check if listing is active or sold out (ie: this means tags)
                    if ([[[[randomListingResultJSON valueForKey:@"results"] valueForKey:@"state"] objectAtIndex:0] isEqualToString:@"sold_out"] || [[[[randomListingResultJSON valueForKey:@"results"] valueForKey:@"state"] objectAtIndex:0] isEqualToString:@"active"]) 
                    {
                        NSArray *listingTags = [[[randomListingResultJSON valueForKey:@"results"] valueForKey:@"tags"] objectAtIndex:0];
                        
                        [spaceFiller setObject:listingTags forKey:[NSString stringWithFormat:@"%i", randomListingID]];
                    }
                } else {
                    NSLog(@"this listing already has been accounted for");
                }
            }
        }   
    }
    
    // dictionary of array holding dictionaries
    [randomListings setObject:spaceFiller forKey:@"spaceFiller"];
    
    [spaceFiller release];
}

#pragma mark - PART 2A: RANDOM USERS

- (void)setUpRandomUsersData
{
    [self resetUsedRandomNumbers];
    
    // get 100 random users
    while ([randomUsersIDs count] != 100) 
    {
        int randomUserID = [self obtainRandomNumber:7];

        NSString *randomUserRequest = [NSString stringWithFormat:@"http://openapi.etsy.com/v2/users/%i/favorites/listings?api_key=%@", randomUserID, newKey.myKey];
        NSString *randomUserResult = [NSString stringWithContentsOfURL:[NSURL URLWithString:randomUserRequest] encoding:NSUTF8StringEncoding error:NULL];
        
        if ([randomUserResult isEqualToString:[NSString stringWithFormat:@"User with PK user_id = %i does not exist", randomUserID]]) {
            NSLog(@"this user does not exist");
        } else if ([randomUserResult isEqualToString:[NSString stringWithFormat:@"Permission denied"]]) {
            NSLog(@"this user has privacy settings enabled");
        } else {
            NSDictionary *randomUserResultJSON = [randomUserResult JSONValue];
            
            // random user needs more than 300 favorites
            // however final count of favorites will likely be less than 300 (see line 168)
            if ([[randomUserResultJSON valueForKey:@"count"] intValue] > 300) {
                    BOOL present = FALSE;
                    
                    for (NSString *randomNumber in usedRandomNumbers)
                    {
                        if ([randomNumber isEqualToString:[NSString stringWithFormat:@"%i", randomUserID]]) present = TRUE;
                    }
                    
                    if (present == FALSE) {
                        NSLog(@"adding user %i", randomUserID);
                        
                        [usedRandomNumbers addObject:[NSString stringWithFormat:@"%i", randomUserID]];
                        [randomUsersIDs addObject:[NSString stringWithFormat:@"%i", randomUserID]];
                    } else {
                        NSLog(@"this user already has been accounted for");
                    }
            } else {
                NSLog(@"this user does not have more than 300 favorites");
            }
        }
    } 
    
    for (NSString *randomUserID in randomUsersIDs) 
    {
        NSMutableDictionary *randomUserFavs = [[NSMutableDictionary alloc] init];
        
        // for each of the pages
        for (int page = 1; page <= 3; page++) 
        {
            NSString *randomUserFavsRequest = [NSString stringWithFormat:@"http://openapi.etsy.com/v2/users/%@/favorites/listings?limit=200&page=%i&api_key=%@", randomUserID, page, newKey.myKey];
            NSString *randomUserFavsResult = [NSString stringWithContentsOfURL:[NSURL URLWithString:randomUserFavsRequest] encoding:NSUTF8StringEncoding error:NULL];
            
            NSDictionary *randomUserFavsResultJSON = [randomUserFavsResult JSONValue];
            
            // for each of the entries
            for (int i = 0; i < [[randomUserFavsResultJSON valueForKey:@"results"] count]; i++) 
            {
                // listing request
                NSString *listingRequest = [NSString stringWithFormat:@"http://openapi.etsy.com/v2/listings/%i?api_key=%@", [[[[randomUserFavsResultJSON valueForKey:@"results"] valueForKey:@"listing_id"] objectAtIndex:i] intValue], newKey.myKey];
                NSString *listingResult = [NSString stringWithContentsOfURL:[NSURL URLWithString:listingRequest] encoding:NSUTF8StringEncoding error:NULL];
                
                NSDictionary *listingResultJSON = [listingResult JSONValue];
                
                // check if listing is active or sold out (ie: this means tags)
                if ([[[[listingResultJSON valueForKey:@"results"] valueForKey:@"state"] objectAtIndex:0] isEqualToString:@"sold_out"] || [[[[listingResultJSON valueForKey:@"results"] valueForKey:@"state"] objectAtIndex:0] isEqualToString:@"active"]) 
                {
                    NSArray *listingTags = [[[listingResultJSON valueForKey:@"results"] valueForKey:@"tags"] objectAtIndex:0];
                    
                    [randomUserFavs setObject:listingTags forKey:[[[randomUserFavsResultJSON valueForKey:@"results"] valueForKey:@"listing_id"] objectAtIndex:i]];
                }
            }
        }
        
        // dictionary of array holding dictionaries
        [randomUsersFavs setObject:randomUserFavs forKey:randomUserID];
        
        [randomUserFavs release];
    }
}

#pragma mark - PART 2B: NOT-SO-RANDOM USERS

- (void)setUpChosenUsersData
{
    for (NSString *chosenUserID in chosenUsers.chosenUserIDs) 
    {
        NSMutableDictionary *chosenUserFavs = [[NSMutableDictionary alloc] init];
        
        // chosen user needs more than 300 favorites
        // however final count of favorites might be less than 300 (see line 358)
        for (int page = 1; page <= 3; page++) 
        {
            NSString *chosenUserFavsRequest = [NSString stringWithFormat:@"http://openapi.etsy.com/v2/users/%@/favorites/listings?limit=200&page=%i&api_key=%@", chosenUserID, page, newKey.myKey];
            NSString *chosenUserFavsResult = [NSString stringWithContentsOfURL:[NSURL URLWithString:chosenUserFavsRequest] encoding:NSUTF8StringEncoding error:NULL];
            
            NSDictionary *chosenUserFavsResultJSON = [chosenUserFavsResult JSONValue];
            
            // for each of the entries
            for (int i = 0; i < [[chosenUserFavsResultJSON valueForKey:@"results"] count]; i++) 
            {
                // listing request
                NSString *listingRequest = [NSString stringWithFormat:@"http://openapi.etsy.com/v2/listings/%i?api_key=%@", [[[[chosenUserFavsResultJSON valueForKey:@"results"] valueForKey:@"listing_id"] objectAtIndex:i] intValue], newKey.myKey];
                NSString *listingResult = [NSString stringWithContentsOfURL:[NSURL URLWithString:listingRequest] encoding:NSUTF8StringEncoding error:NULL];
                
                NSDictionary *listingResultJSON = [listingResult JSONValue];
                
                // check if listing is active or sold out (ie: this means tags)
                if ([[[[listingResultJSON valueForKey:@"results"] valueForKey:@"state"] objectAtIndex:0] isEqualToString:@"sold_out"] || [[[[listingResultJSON valueForKey:@"results"] valueForKey:@"state"] objectAtIndex:0] isEqualToString:@"active"]) 
                {
                    NSArray *listingTags = [[[listingResultJSON valueForKey:@"results"] valueForKey:@"tags"] objectAtIndex:0];
                    
                    [chosenUserFavs setObject:listingTags forKey:[[[chosenUserFavsResultJSON valueForKey:@"results"] valueForKey:@"listing_id"] objectAtIndex:i]];
                }
            }
        }
        
        // dictionary of array holding dictionaries
        [chosenUsersFavs setObject:chosenUserFavs forKey:chosenUserID];
        
        NSLog(@"%lu", [chosenUsersFavs count]);
        
        [chosenUserFavs release];
    }
}

@end
