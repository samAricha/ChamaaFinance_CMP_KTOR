package com.teka.chamaa_finance.screens.trx.model

import xyz.mcxross.kaptos.Aptos
import xyz.mcxross.kaptos.account.Account
import xyz.mcxross.kaptos.extension.toStructTag
import xyz.mcxross.kaptos.model.HexInput
import xyz.mcxross.kaptos.model.MoveString
import xyz.mcxross.kaptos.model.TypeTagStruct
import xyz.mcxross.kaptos.model.U64
import xyz.mcxross.kaptos.model.entryFunctionData
import xyz.mcxross.kaptos.model.functionArguments
import xyz.mcxross.kaptos.model.typeArguments

suspend fun transfer(aptos: Aptos, sender: Account, receiver: Account, amount: Int) {
  val txn =
    aptos.buildTransaction.simple(
      sender = sender.accountAddress,
      data =
        entryFunctionData {
          function = "0x1::coin::transfer"
          typeArguments = typeArguments {
            +TypeTagStruct(type = "0x1::aptos_coin::AptosCoin".toStructTag())
          }
          functionArguments = functionArguments {
            +MoveString(receiver.accountAddress.toString())
            +U64((amount.toLong() * 100_000_000).toULong())
          }
        },
    )

  // Sign and submit the transaction
  val commitedTransaction = aptos.signAndSubmitTransaction(sender, txn)

  val executedTransaction =
    aptos.waitForTransaction(
      HexInput.fromString(commitedTransaction.expect("Transaction failed").hash)
    )
}
