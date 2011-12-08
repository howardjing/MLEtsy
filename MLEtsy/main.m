//
//  main.m
//  MLEtsy
//
//  Created by Howard Jing on 12/3/11.
//  Copyright 2011 NYU. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "DataManager.h"

int main (int argc, const char * argv[])
{
    NSAutoreleasePool * pool = [[NSAutoreleasePool alloc] init];
    
    DataManager *newDataManager = [[DataManager alloc] init];
    
    [newDataManager release];
    
//    // SPECIFIC USER
//    
//    NSMutableArray *allListingIDs = [[NSMutableArray alloc] init];
//    
//    // for each of the pages
//    for (int page = 1; page <= 44; page++) 
//    {
//        NSString *userListingRequest = [NSString stringWithFormat:@"http://openapi.etsy.com/v2/users/corduroy/favorites/listings?limit=4317&page=%i&api_key=%@", page, newKey.myKey];
//        NSString *userListingResult = [NSString stringWithContentsOfURL:[NSURL URLWithString:userListingRequest] encoding:NSUTF8StringEncoding error:NULL];
//    
//        NSDictionary *userListingResultDictionary = [userListingResult JSONValue];
//    
//        // for each of the entries
//        for (int i = 0; i < [[userListingResultDictionary valueForKey:@"results"] count]; i++) 
//        {
//            [allListingIDs addObject:[[[userListingResultDictionary valueForKey:@"results"] valueForKey:@"listing_id"] objectAtIndex:i]];
//        }
//    }
//    
//    // for all of the listing IDs
//    for (int listingID = 0; listingID < [allListingIDs count]; listingID++) 
//    {
//        // listing request
//        NSString *listingRequest = [NSString stringWithFormat:@"http://openapi.etsy.com/v2/listings/%i?api_key=%@", [[allListingIDs objectAtIndex:listingID] intValue], newKey.myKey];
//        NSString *listingResult = [NSString stringWithContentsOfURL:[NSURL URLWithString:listingRequest] encoding:NSUTF8StringEncoding error:NULL];
//        
//        NSDictionary *listingResultDictionary = [listingResult JSONValue];
//        
//        // check if listing is active or sold out (ie: this means tags)
//        if ([[[[listingResultDictionary valueForKey:@"results"] valueForKey:@"state"] objectAtIndex:0] isEqualToString:@"sold_out"] || [[[[listingResultDictionary valueForKey:@"results"] valueForKey:@"state"] objectAtIndex:0] isEqualToString:@"active"] ) 
//        {
//            NSArray *listingTags = [[[listingResultDictionary valueForKey:@"results"] valueForKey:@"tags"] objectAtIndex:0];
//        
//            // print listing ID
//            NSLog(@"%@", [NSString stringWithFormat:@"%@:", [allListingIDs objectAtIndex:listingID]]);
//  
//            for (int listingTag = 0; listingTag < [listingTags count]; listingTag++) 
//            {
//                // print listing tags
//                NSLog(@"%@", [listingTags objectAtIndex:listingTag]);
//            }
//        }
//    }
//    
//    // RANDOM LISTINGS
//    
//    NSMutableArray *usedRandomNumbers = [[NSMutableArray alloc] init];
//    
//    NSMutableArray *allRandomListingIDs = [[NSMutableArray alloc] init];
//    
//    // getting 8000 random listings
//    while ([allRandomListingIDs count] != 8000) 
//    {
//        NSString *randomNumber = @"";
//        
//        for (int i = 0; i < 8; i++) 
//        {
//            int randomDigit = arc4random() % 9;
//            
//            NSString *intString = [NSString stringWithFormat:@"%i", randomDigit];
//            randomNumber = [randomNumber stringByAppendingString:intString];
//        }
//        
//        int randomListingID = [randomNumber intValue];
//        
//        NSString *randomListingRequest = [NSString stringWithFormat:@"http://openapi.etsy.com/v2/listings/%i?api_key=%@", randomListingID, newKey.myKey];
//        NSString *randomListingResult = [NSString stringWithContentsOfURL:[NSURL URLWithString:randomListingRequest] encoding:NSUTF8StringEncoding error:NULL];
//        
//        if ([randomListingResult isEqualToString:[NSString stringWithFormat:@"Translation_TranslatableFinder: Listing-&gt;find(%i) does not exist", randomListingID]]) {
//            NSLog(@"this listing does not exist");
//        } else {
//            NSDictionary *randomListingResultDictionary = [randomListingResult JSONValue];
//
//            // for each of the entries
//            for (int i = 0; i < [[randomListingResultDictionary valueForKey:@"results"] count]; i++) 
//            {
//                BOOL present = FALSE;
//                
//                for (NSString* aRandomNumber in usedRandomNumbers)
//                {
//                    if ([aRandomNumber isEqualToString:randomNumber]) 
//                    {
//                        present = TRUE;
//                    }
//                }
//                
//                if (present == FALSE) {
//                    [allRandomListingIDs addObject:[[[randomListingResultDictionary valueForKey:@"results"] valueForKey:@"listing_id"] objectAtIndex:i]];
//                    [usedRandomNumbers addObject:randomNumber];
//                } else {
//                    NSLog(@"this listing already has been accounted for");
//                }
//            }
//        }
//    }
//    
//    // for all of the listing IDs
//    for (int listingID = 0; listingID < [allRandomListingIDs count]; listingID++) 
//    {
//        // listing request
//        NSString *listingRequest = [NSString stringWithFormat:@"http://openapi.etsy.com/v2/listings/%i?api_key=%@", [[allRandomListingIDs objectAtIndex:listingID] intValue], newKey.myKey];
//        NSString *listingResult = [NSString stringWithContentsOfURL:[NSURL URLWithString:listingRequest] encoding:NSUTF8StringEncoding error:NULL];
//        
//        NSDictionary *listingResultDictionary = [listingResult JSONValue];
//        
//        // check if listing is active or sold out (ie: this means tags)
//        if ([[[[listingResultDictionary valueForKey:@"results"] valueForKey:@"state"] objectAtIndex:0] isEqualToString:@"sold_out"] || [[[[listingResultDictionary valueForKey:@"results"] valueForKey:@"state"] objectAtIndex:0] isEqualToString:@"active"] ) 
//        {
//            NSArray *listingTags = [[[listingResultDictionary valueForKey:@"results"] valueForKey:@"tags"] objectAtIndex:0];
//            
//            //print listing ID
//            NSLog(@"%@", [NSString stringWithFormat:@"%@:", [allRandomListingIDs objectAtIndex:listingID]]);
//            
//            for (int listingTag = 0; listingTag < [listingTags count]; listingTag++) 
//            {
//                //print listing tags
//                NSLog(@"%@", [listingTags objectAtIndex:listingTag]);
//            }
//        }
//    }
    
    [pool drain];
    return 0;
}
