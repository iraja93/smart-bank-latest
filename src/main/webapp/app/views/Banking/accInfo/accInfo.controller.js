/**
 * Created by adhabale on 4/4/2017.
 */
/**
 * Created by SouvikDa on 3/7/2017.
 */
(function() {
	'use strict';

	angular
	.module('smartbankApp')
	.controller('DetailsController', DetailsController);

	DetailsController.$inject = ['$scope','$state','$http','$localStorage','$timeout'];

	function DetailsController($scope,$state,$http,$localStorage,$timeout) {

		var vm = this;
		vm.payee={};
		vm.fundT={};
		vm.billPayment={};
		vm.visible=false;
		vm.visibleFund=false;
		vm.visiblePay=false;
		
		


		$http.get('/api/accountId').then(function (response) {
			vm.myLogIn=response.data;
			//lid=vm.myLogIn.id
			var first;
			for (var key in vm.myLogIn)
			{
			   if (vm.myLogIn.hasOwnProperty(key))
			   {
			      // here you have access to
			      var l = vm.myLogIn[key].id;
			      $localStorage.id=l;
			      //console.log(key);
			     
			   }
			   break;
			}
			
			
			//$localStorage.id=Object.keys(vm.myLogIn)[0].id;
			
			

			//$localStorage.id=vm.myLogIn.id;
			
		})

		var lid=$localStorage.id;

		//console.log("Hiiiii"+lid);

		vm.pay=function(id,name,amount,dDate,cNo,details){
			vm.billPayment.billAmount=amount;
			vm.billPayment.billId=id;
			vm.billPayment.cardNo=cNo.id;

			$http({
				method: 'POST',
				url: '/api/payPendingBills',
				data: vm.billPayment
			}

			)
			.success(function(response){
				vm.status=response.data;
				alert("Your Payment is Successful");
				$http.get('/api/getBillsDetailsByAccountNo/'+lid).then(function (response) {
					vm.bills=response.data;
				})



				//console.log(status);
				//alert("Your Payment is Successful");
			});

		}


		$http.get('/api/b-accounts/1').then(function (response) {
			vm.myData=response.data;
			// console.log(vm.myData);
		})
		$http.get('/api/getAllTransaction/'+lid).then(function (response) {
			vm.transacs=response.data;
			//  console.log(vm.transacs);
		})

		$http.get('/api/getlastTransactionDetails/'+lid).then(function (response) {
			vm.ltransacs=response.data;
		})

		$http.get('/api/getBenificiariesByAccountId/'+lid).then(function (response) {
			vm.beneficiary=response.data;
			//console.log(vm.beneficiary);
		})

		$http.get('/api/getBillsDetailsByAccountNo/'+lid).then(function (response) {
			vm.bills=response.data;

		})
		
		$http.get('/api/getCardsByAccountId/'+lid).then(function (response) {
			vm.card=response.data;
			//console.log(vm.card);
		})
		
      
		vm.send=function (name,amount) {
			name=name.beneficiaryName;
			amount=vm.amount;
			// console.log("jhjhjkh"+name+''+amount);
		}

		vm.addBenef=function(){
			//console.log(vm.bacc);


			/* $http.post('/api/transaction/1').then(function (response) {
                 vm.transacs=response.data;
                //  console.log(vm.transacs);
             })*/
			vm.payee.bAccountId=lid;
			//vm.payee.id=0;
			//console.log(lid);
			$http({
				method: 'POST',
				url: '/api/addBenificiaries',
				data: vm.payee
			}

			)
			.success(function(response){
				//vm.status=response.data;
				//alert("Payee Added Succesfully");
				vm.visible=true;
				$timeout(function () {vm.visible = false; }, 3000);

			});


		}

		vm.send=function (name,amount) {
			//name=name.payeeAccNo;
			//amount=vm.fundT.amount;
			//console.log("jhjhjkh"+amount);
			vm.fundT.senderId=lid;
			vm.fundT.receiverId=name.accountId;
			console.log(vm.fundT);
			$http({
				method: 'POST',
				url: '/api/fundTransfer',
				data: vm.fundT
			}

			)
			.success(function(response){
				//vm.status=response.data;
				//alert("Fund Transfer Succesfully");
				vm.visibleFund=true;
				$timeout(function () {vm.visibleFund = false; }, 3000);
			});

		}

		vm.payBill=function ( ) {
			vm.bill.senderId=lid;
			vm.bill.id=id.id;
			vm.bill.billAmount=billAmount;
			$http({
				method: 'POST',
				url: '/api/billTransfer',
				data: vm.bill
			}

			)
			.success(function(response){
				//alert("Pay Bill Succesfully");
				vm.visiblePay=true;

			})
			.error(deferred.reject);

		}
		vm.fundTransfer=function(id,amount) {


			id=receiverId;
			//console.log(id);
			vm.fundT.senderId=id;
			$http({
				method: 'POST',
				url: '/api/fundTransfer',
				data: vm.fundT
			}

			)
			.success(function(response){
				//vm.status=response.data;
				alert("Fund Transfer Succesfully");
			})
			.error(function (data, status, headers, config) {
		        //alert("error");
		        return status;
		});


		};

		vm.deletePayee=function (payeeAccNo,id){

			vm.delPayee={};
			vm.delPayee.id=payeeAccNo;
			//console.log("Delete");
			$http({
				method: 'DELETE',
				url: '/api/deleteBenificiaries/'+vm.delPayee.id,
				
			}

			)
			.success(function(response){
				//vm.status=response.data;
				alert("Deleted Succesfully");
				$http.get('/api/getBenificiariesByAccountId/'+lid).then(function (response) {
					vm.beneficiary=response.data;
					//console.log(vm.beneficiary);
				})
			});
		}

	}


})();

