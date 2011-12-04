//
//  main.m
//  MLEtsy
//
//  Created by Howard Jing on 12/3/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "SBJson.h"
#import "Key.h"

int main (int argc, const char * argv[])
{
    NSAutoreleasePool * pool = [[NSAutoreleasePool alloc] init];
    
    Key *newKey = [[Key alloc] init];
    
    NSMutableArray *allListingIDs = [[NSMutableArray alloc] init];
    
    for (int count = 1; count <= 44; count++) 
    {
        NSLog(@"%i", count);
        
        //this is only page one, will need to go through all pages (ie: 4317/100)...
        NSString *userListingRequest = [NSString stringWithFormat:@"http://openapi.etsy.com/v2/users/corduroy/favorites/listings?limit=4317&page=%i&api_key=%@", count, newKey.myKey];
        NSString *userListingResult = [NSString stringWithContentsOfURL:[NSURL URLWithString:userListingRequest] encoding:NSUTF8StringEncoding error:NULL];
    
        //the JSON dictionary
        NSDictionary *userListingResultDictionary = [userListingResult JSONValue];
    
        //for each of the entries
        for (int i = 0; i < [[userListingResultDictionary valueForKey:@"results"] count]; i++) 
        {
            [allListingIDs addObject:[[[userListingResultDictionary valueForKey:@"results"] valueForKey:@"listing_id"] objectAtIndex:i]];
        }
    }
    
    for (int other = 0; other < [allListingIDs count]; other++) 
    {
        
        //listing request
        NSString *listingRequest = [NSString stringWithFormat:@"http://openapi.etsy.com/v2/listings/%i?api_key=%@", [[allListingIDs objectAtIndex:other] intValue], newKey.myKey];
        NSString *listingResult = [NSString stringWithContentsOfURL:[NSURL URLWithString:listingRequest] encoding:NSUTF8StringEncoding error:NULL];
        
        NSDictionary *listingResultDictionary = [listingResult JSONValue];
    if (![[[[listingResultDictionary valueForKey:@"results"] valueForKey:@"state"] objectAtIndex:0] isEqualToString:@"unavailable"]) {
        NSArray *listingTags = [[[listingResultDictionary valueForKey:@"results"] valueForKey:@"tags"] objectAtIndex:0];
        
        NSString *string = [NSString stringWithFormat:@"%@:", [allListingIDs objectAtIndex:other]];
        NSLog(@"%@", string);
  
            for (int blah = 0; blah < [listingTags count]; blah++) {
    
                NSLog(@"%@", [listingTags objectAtIndex:blah]);
            }
        

        }
    }

    [pool drain];
    return 0;
}

