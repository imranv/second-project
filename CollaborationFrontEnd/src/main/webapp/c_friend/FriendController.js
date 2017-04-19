'use strict';

app.controller("FriendController", ['UserService','$scope','FriendService','$location','$rootScope',   
                                    function(UserService,$scope,FriendService,$location,$rootScope){
	console.log("FriendController...")
  	var self = this;
	self.friend ={
			id : '',
			userID : '',
			friendID : '',
			status : '',
			isOnline : '',
			errorMessage : '',
			errorCode : ''
	};
	self.friends = [];
	self.friendRequests=[];
	self.acceptedFriends=[];
	self.user = {
			id : '',
			name :'',
			email : '',
			password : '',
			mob_no : '',
			dob : '',
			gender : '',
			role : '',
			errorMessage : '',
			errorCode : ''
	};
	self.users=[];
    
    self.sendFriendRequest=function(fid){
   	 self.createFriend(fid);
    };
   	 self.createFriend=function(fid){
   		 FriendService.sendFriendRequest(fid).then( 
   					 console.log('friend is created'),
   					 self.fetchAllFriends,
   					 function(errResponse)
   					 {
   						 console.error('friend is not created');
   					 }
   					 )
   					 
   	 }; 
   	self.updateFriendRequest = function(friend,id){
   		FriendService.updateFriendRequest(friend,id)
   		.then(
   				self.fetchAllFriends,
   				function(errResponse){
   					console.error('Error while updating friend');
   				}
   				);
   	} ;
   	 
   	 self.deleteFriend = function(id){
   		 FriendService.deleteFriend(id)
   		 .then(
   				 self.fetchAllFriends,
   				 function(errResponse){
   					 console.error('Error while deleting friend');
   				 }
   				 );
   	 };
   	 
   	 
   	 
   	 self.fetchAllUsers=function(){
    		UserService.fetchAllUsers().then(function(d){
    			self.users=d;
    		},
    		function(errResponse){
    			console.error('error while fetching Users');
    		}
    		)
    	};
    	
    	self.fetchAllUsers();
   	 
   	self.fetchAllFriends=function(){
   		FriendService.getMyFriends().then(function(d){
   			self.friends=d;
   		},
   		function(errResponse){
   			console.error('error while fetching Friends');
   		}
   		)
   	};
   	//self.fetchAllFriends();
   	self.getMyFriendRequests=function(){
   		console.log("getting my friend requests")
   		FriendService.getMyFriendRequests()
   		.then(function(d){
   			self.friendRequests=d;
   			console.log("got the friend requests")
   		},
   		function(errResponse){
   			console.error('error while fetching friends ');
   		})
   	};
   	self.getMyFriendRequests();
   	
   	
   	self.acceptfriend=function(fid){
   		FriendService.acceptFriendRequest(fid)
   		.then(function(d){
   			self.friends=d;
   			self.getMyFriendRequests();
   			alert("Friend request Accepted")
   		},
   		function(errResponse){
   			console.error('error while sending the friend request ');
   		}
   		)
   	};
   	self.rejectfriend=function(fid){
   		FriendService.rejectFriendRequest(fid)
   		.then(function(d){
   			self.friends=d;
   			self.getMyFriendRequests();
   			alert("Friend request rejected")
   		},
   		function(errResponse){
   			console.error('error while sending the friend request ');
   		})
   	};
  	self.getMyFriends=function(){
   		console.log("getting my friends")
   		FriendService.getMyFriends()
   		.then(function(d){
   			self.acceptedFriends=d;
   			console.log(self.acceptedFriends)
   			console.log("got my friends list")
   		},
   		function(errResponse){
   			console.error('error while fetching my friends ');
   		})
   	};
   	self.getMyFriends();
   	
}]); 	