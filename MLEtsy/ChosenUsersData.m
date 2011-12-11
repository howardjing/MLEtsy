//
//  ChosenUsersData.m
//  MLEtsy
//
//  Created by Shirmung Bielefeld on 12/11/11.
//  Copyright 2011 NYU. All rights reserved.
//

#import "ChosenUsersData.h"

@implementation ChosenUsersData

@synthesize chosenUserIDs;

- (id)init
{
    self = [super init];
    
    if (self) 
    {
        // Initialization code here.
        
        // get 100 users chosen by our user's top 10 favorite categories
        // 21 users that have favorited vintage item 60382018
        NSArray *vintageTemp = [[NSArray alloc] initWithObjects:@"ArtlessByBeka", @"danca2008", @"anilumagloire", @"piabarile", @"bayword123", @"kaygeo", @"claylicious", @"amandinebukuru", @"dietgrrrl", @"kirstenalicia", @"shannonpix", @"WindriderLirian", @" VictoriaGracexo", @"Luckytourist", @"borbi", @"laurenblythedesigns", @"TarynC", @"hellageorge", @"lkaulbach", @"alexiskalin", @"thechicrebel", nil];
        
        // 16 users that have favorited art item 61476504
        NSArray *artTemp = [[NSArray alloc] initWithObjects:@"Nippes", @"littletjane", @"SakePuppets", @"TheRoundWindow", @"alxmalx", @"Mariinoushka", @"huffw", @"vyte", @"krize", @"gonchi", @"whatkatiedid89", @"ArgyleWhale", @"ErikaElyre", @"blueflamingstockings", @"ristanc", @"bibabo", nil];
        
        // 10 users that have favorited bags and purses item 78219333
        NSArray *bagsAndPursesTemp = [[NSArray alloc] initWithObjects:@"theshopofawesome", @"Aramar", @"whatnomints", @"nanoukiko", @"emofoFashionDesing", @"MayThailand", @"octobeer", @"rabbitho", @"Thyca", @"evamic", nil];
        
        // 10 users that have favorited home decor item 62592106
        NSArray *homeDecorTemp = [[NSArray alloc] initWithObjects:@"crazyminime23", @"robertschelsea", @"Lluvyi", @"Kiki1013", @"sofiabin", @"teawithsquids", @"svolech", @"kfiring", @"kb2mbh", @"lacocorouge", nil];
        
        // 9 users that have favorited clothing item 73912039
        NSArray *clothingTemp = [[NSArray alloc] initWithObjects:@"14xbags", @"chelseyboker", @"fialivesinneverland", @"iloveyoujewels", @"mismm2322", @"PersimmonDreams", @"lolotte32", @"DeerBee", @"ThePurpleMaster", nil];
        
        // 9 users that have favorited housewares item 62668742
        NSArray *housewaresTemp = [[NSArray alloc] initWithObjects:@"chebby", @"k1hartley", @"dottownsend", @"GBILOBA", @"eyemason", @"luckywahine", @"mackenzieframes", @"pinkcupcake", @"mary11428", nil];
        
        // 7 users that have favorited print item 64855067
        NSArray *printTemp = [[NSArray alloc] initWithObjects:@"cloudalexis", @"onstail", @"hozzlebozzle", @"Shergold", @"PoplinHomewear", @"sallydarity", @"sweetdeereynolds", nil];
        
        // 6 users that have favorited women item 77479991
        NSArray *womenTemp = [[NSArray alloc] initWithObjects:@"vealkutlets", @"TarasBoutique", @"husavik", @"lizziwood", @"rockbottomglass", @"TRACCE", nil];
        
        // 6 users that have favorited white item 62772043
        NSArray *whiteTemp = [[NSArray alloc] initWithObjects:@"kewilliams2", @"Albahandmadeforyou", @"Gallivants", @"chicretro", @"knittingsandbox", @"Alaroycreature", nil];
        
        // 5 users that have favorited illustration item 64002509
        NSArray *illustrationTemp = [[NSArray alloc] initWithObjects:@"theYarnKitchen", @"ginaregina", @"leathercrafts1", @"WILDMOONROSEJEWELRY", @"CreatureCollars", nil];

        chosenUserIDs = [[NSMutableArray alloc] init];
        
        [chosenUserIDs addObjectsFromArray:vintageTemp];
        [chosenUserIDs addObjectsFromArray:artTemp];
        [chosenUserIDs addObjectsFromArray:bagsAndPursesTemp];
        [chosenUserIDs addObjectsFromArray:homeDecorTemp];
        [chosenUserIDs addObjectsFromArray:clothingTemp];
        [chosenUserIDs addObjectsFromArray:housewaresTemp];
        [chosenUserIDs addObjectsFromArray:printTemp];
        [chosenUserIDs addObjectsFromArray:womenTemp];
        [chosenUserIDs addObjectsFromArray:whiteTemp];
        [chosenUserIDs addObjectsFromArray:illustrationTemp];
        
        [vintageTemp release];
        [artTemp release];
        [bagsAndPursesTemp release];
        [homeDecorTemp release];
        [clothingTemp release];
        [housewaresTemp release];
        [printTemp release];
        [womenTemp release];
        [whiteTemp release];
        [illustrationTemp release];
    }
    
    return self;
}

@end
