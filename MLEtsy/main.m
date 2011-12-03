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

    //this is only page one, will need to go through all pages (ie: 4317/100)...
    NSString *userListingRequest = [NSString stringWithFormat:@"http://openapi.etsy.com/v2/users/corduroy/favorites/listings?limit=4317&page=%i&api_key=%@", 1, newKey.myKey];
    NSString *userListingResult = [NSString stringWithContentsOfURL:[NSURL URLWithString:userListingRequest] encoding:NSUTF8StringEncoding error:NULL];
    
    //the JSON dictionary
    NSDictionary *userListingResultDictionary = [userListingResult JSONValue];
    
    //for each of the entries
    for (int i = 0; i < [[userListingResultDictionary valueForKey:@"results"] count]; i++) 
    {
        int listingID = [[[[userListingResultDictionary valueForKey:@"results"] valueForKey:@"listing_id"] objectAtIndex:i] intValue];
        
        NSLog(@"%i", listingID);
    }

    [pool drain];
    return 0;
}

