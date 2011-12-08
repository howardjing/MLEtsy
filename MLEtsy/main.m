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
    
    [pool drain];
    return 0;
}
