//
//  main.m
//  MLEtsy
//
//  Created by Howard Jing on 12/3/11.
//  Copyright 2011 NYU. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "SBJson.h"
#import "Key.h"

int main (int argc, const char * argv[])
{
    NSAutoreleasePool * pool = [[NSAutoreleasePool alloc] init];
    
    Key *newKey = [[Key alloc] init];
    
    NSMutableArray *allListingIDs = [[NSMutableArray alloc] init];
    
    // for each of the pages
    for (int page = 1; page <= 44; page++) 
    {
        NSString *userListingRequest = [NSString stringWithFormat:@"http://openapi.etsy.com/v2/users/corduroy/favorites/listings?limit=4317&page=%i&api_key=%@", page, newKey.myKey];
        NSString *userListingResult = [NSString stringWithContentsOfURL:[NSURL URLWithString:userListingRequest] encoding:NSUTF8StringEncoding error:NULL];
    
        NSDictionary *userListingResultDictionary = [userListingResult JSONValue];
    
        // for each of the entries
        for (int i = 0; i < [[userListingResultDictionary valueForKey:@"results"] count]; i++) 
        {
            [allListingIDs addObject:[[[userListingResultDictionary valueForKey:@"results"] valueForKey:@"listing_id"] objectAtIndex:i]];
        }
    }
    
    // for all of the listing IDs
    for (int listingID = 0; listingID < [allListingIDs count]; listingID++) 
    {
        // listing request
        NSString *listingRequest = [NSString stringWithFormat:@"http://openapi.etsy.com/v2/listings/%i?api_key=%@", [[allListingIDs objectAtIndex:listingID] intValue], newKey.myKey];
        NSString *listingResult = [NSString stringWithContentsOfURL:[NSURL URLWithString:listingRequest] encoding:NSUTF8StringEncoding error:NULL];
        
        NSDictionary *listingResultDictionary = [listingResult JSONValue];
        
        // check if listing is unavailable or not (ie: this would mean no tags)
        if (![[[[listingResultDictionary valueForKey:@"results"] valueForKey:@"state"] objectAtIndex:0] isEqualToString:@"unavailable"]) 
        {
            NSArray *listingTags = [[[listingResultDictionary valueForKey:@"results"] valueForKey:@"tags"] objectAtIndex:0];
        
            //print listing ID
            NSLog(@"%@", [NSString stringWithFormat:@"%@:", [allListingIDs objectAtIndex:listingID]]);
  
            for (int listingTag = 0; listingTag < [listingTags count]; listingTag++) 
            {
                //print listing tags
                NSLog(@"%@", [listingTags objectAtIndex:listingTag]);
            }
        }
    }

    [pool drain];
    return 0;
}

