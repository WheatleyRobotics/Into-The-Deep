package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.subsystems.Drive;

import dev.frozenmilk.dairy.core.util.features.BulkRead;
import dev.frozenmilk.mercurial.Mercurial;

@Mercurial.Attach
@Drive.Attach
@BulkRead.Attach
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp")
public class TeleOp extends OpMode {

     @Override
        public void init() {
            //Operator
        }

        @Override
        public void loop() {
        }
}