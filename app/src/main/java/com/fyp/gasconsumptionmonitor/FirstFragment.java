package com.fyp.gasconsumptionmonitor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.Scanner;

import static java.lang.Math.pow;

public class FirstFragment extends Fragment {

    int N;
    int meter;
    double flatRate;
    int Meter_Rent;
    double NewMeterRent_M;
    double NewMeterRent_L;
    double MeterRent;
    double meter_R;
    double totalGasCharges;
    double Tax;
    double totalPayble_Bill;
    double Slabrate;
    double MMBTU;
    double x;
    double mmbtu;
    int Slab_rate;
    double Gas_consumption;
    double Consump_Gas;
    double mmbtu_1;
    double i;
    double S_R;
    double TB;
    double Final_Gcharges;
    double Gas_Charges;
    double MinChg;

    double y;
    double j;

    /*Slab Variables */
    int Slab_Rate1 = 121;
    int Slab_Rate2 = 300;
    int Slab_Rate3 = 553;
    int Slab_Rate4 = 738;
    int Slab_Rate5 = 1107;
    int Slab_Rate6 = 1460;

    double Slab_0 = 0.0;			/*Slab Upto; According to 30 days */
    double Slab_1 = 0.5;			/*Slab Upto; According to 30 days */
    double Slab_2 = 1.0;			/*Slab Upto; According to 30 days */
    double Slab_3 = 2.0;			/*Slab Upto; According to 30 days */
    double Slab_4 = 3.0;			/*Slab Upto; According to 30 days */
    double Slab_5 = 4.0;			/*Slab Upto; According to 30 days */

    double newSlab_l1;
    double newSlab_l2;
    double newSlab_l3;
    double newSlab_l4;
    double newSlab_l5;

    /*Minimum Charges */
    double domestic1_MinCharges = 172.58;
    double domestic2_MinCharges = 3900.00;
    double Commercial_MinCharges = 6415.00;
    double SpecialCommercial_MinCharges = 172.58;
    double IceFactories_MinCharges = 6415.00;
    double GeneralIndustries_MinCharges = 36449.70;
    double Manufacturers_MinCharges = 28060.20;
    double CNG_MinCharges = 45803.10;

    double newdomestic1_MinCharges;
    double newdomestic2_MinCharges;
    double newCommercial_MinCharges;
    double newSpecialCommercial_MinCharges;
    double newIceFactories_MinCharges;
    double newGeneralIndustries__MinCharges;
    double newManufacturers_MinCharges;
    double newCNG_MinCharge;
    int GCV=1500;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Scanner input = new Scanner(System.in);

                System.out.println("Enter Number of days for which bill is being calculated: ");
                N = input.nextInt();

                //Input stored in firebase
                System.out.println("Enter your meter type:");
                System.out.println("1.Domestic_1");
                System.out.println("2.Domestic_2");
                meter = input.nextInt();

                switch (meter)
                {
                    case 1:
                        System.out.println("Domestic_1");
                        finalCalculations ();
                        break;
                    case 2:
                        System.out.println("Domestic_2");
                        Calculations();
                        break;
                    case 3:
                        System.out.println("Commercial");
                        Calculations();
                        break;
                    case 4:
                        System.out.println("Special Commercial");
                        Calculations();
                        break;
                    case 5:
                        System.out.println("Ice Factories");
                        Calculations();
                        break;
                    case 6:
                        System.out.println("General Industries");
                        Calculations();
                        break;
                    case 7:
                        System.out.println("Manufacturers");
                        Calculations();
                        break;
                    case 8:
                        System.out.println("CNG");
                        Calculations();
                        break;
                    default:
                        System.out.println("You have entered an invlaid meter type");
                }
                /*NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);*/
            }
        });

    }
    /* Calculatng MMBTU*/
    public double Calcualting_MMBTU() {

        double PressureFactor;
        double P;
        Scanner input = new Scanner(System.in);
        P = 0.5;
        PressureFactor = (P + 14.65) / 14.65;
        System.out.println("The Pressure Factor is" + PressureFactor);


        double Previous_Reading;
        double Current_Reading;
        System.out.println("Previous Reading = ");
        Previous_Reading = input.nextDouble();
        System.out.println("Current Reading = ");
        Current_Reading = input.nextDouble();
        double D;
        double D_hm3;
        D = Current_Reading - (Previous_Reading);
        D_hm3 = D * pow (10, -5);
        System.out.println("The differnce in Hm3 is" + D_hm3);

        Gas_consumption = PressureFactor * D_hm3;
        System.out.println("The gas consumption is" + Gas_consumption);

        i = Gas_consumption;

        /*cout<<("Consump_Gas=%f\n",i); */

        MMBTU = (i * GCV);
        /*cout<<("\n%f\n",MMBTU); */
        mmbtu_1 = (MMBTU / 281.7385);
        System.out.println(" The MMBTU calculated is" + mmbtu_1);
        return mmbtu_1;
    }

    /*code to calculate slab for Domestic_1*/
    int SlabRate_E30 (int Slab_rate){

        if (i >= Slab_0 && i <= Slab_1)
        {
            System.out.println("The Slab Rate per MMBTU is " + Slab_Rate1);
            Slab_rate = Slab_Rate1;

        }

        else if (i > Slab_1 && i <= Slab_2)
        {
            System.out.println("The Slab Rate per MMBTU is " + Slab_Rate2);
            Slab_rate = Slab_Rate2;
        }

        else if (i > Slab_2 && i <= Slab_3)
        {
            System.out.println("The Slab Rate per MMBTU is " + Slab_Rate3);
            Slab_rate = Slab_Rate3;
        }

        else if (i > Slab_3 && i <= Slab_4)
        {
            System.out.println("The Slab Rate per MMBTU is " + Slab_Rate4);
            Slab_rate = Slab_Rate4;
        }

        else if (i > Slab_4 && i <= Slab_5)
        {
            System.out.println("The Slab Rate per MMBTU is " + Slab_Rate5);
            Slab_rate = Slab_Rate5;

        }

        else
        {
            System.out.println("The Slab Rate per MMBTU is " + Slab_Rate6);
            Slab_rate = Slab_Rate6;

        }
        return Slab_rate;
    }

    int Slabrate_L30 (int Slab_rate){
        /*Slabs for days less than 30 */
        newSlab_l1 = (Slab_1 * N) / 30;
        newSlab_l2 = (Slab_2 * N) / 30;
        newSlab_l3 = (Slab_3 * N) / 30;
        newSlab_l4 = (Slab_4 * N) / 30;
        newSlab_l5 = (Slab_5 * N) / 30;

        System.out.println("The New slab division is as follows:");
        System.out.println("Sale Price                                 Rs/MMBTU");
        System.out.println("Upto " + newSlab_l1+"hm3 per month         121.00Rs");
        System.out.println("Upto " + newSlab_l2+"hm3 per month         300.00Rs");
        System.out.println("Upto " + newSlab_l3+"hm3 per month         553.00Rs");
        System.out.println("Upto " + newSlab_l4+"hm3 per month         738.00Rs");
        System.out.println("Upto " + newSlab_l5+"hm3 per month         1107.00Rs");
        System.out.println("Upto " + newSlab_l5+"hm3 per month         1460.00Rs");

        if (i >= Slab_0 && i <= newSlab_l1)
        {
            System.out.println("The Slab Rate per MMBTU is " + Slab_Rate1);
            Slab_rate = Slab_Rate1;

        }

        else if (i > newSlab_l1 && i <= newSlab_l2)
        {
            System.out.println("The Slab Rate per MMBTU is " + Slab_Rate2);
            Slab_rate = Slab_Rate2;

        }

        else if (i > newSlab_l2 && i <= newSlab_l3)
        {
            System.out.println("The Slab Rate per MMBTU is " + Slab_Rate3);
            Slab_rate = Slab_Rate3;

        }

        else if (i > newSlab_l3 && i <= newSlab_l4)
        {
            System.out.println("The Slab Rate per MMBTU is " + Slab_Rate4);
            Slab_rate = Slab_Rate4;

        }

        else if (i > newSlab_l4 && i <= newSlab_l5)
        {
            System.out.println("The Slab Rate per MMBTU is " + Slab_Rate5);
            Slab_rate = Slab_Rate5;

        }

        else
        {
            System.out.println("The Slab Rate per MMBTU is " + Slab_Rate6);
            Slab_rate = Slab_Rate6;

        }
        return Slab_rate;
    }

    int SlabRate_M30 (int Slab_rate){
        /*Slabs for days greater than 30 */
        double newSlab_g1;
        double newSlab_g2;
        double newSlab_g3;
        double newSlab_g4;
        double newSlab_g5;

        newSlab_g1 = (Slab_1 * N) / 30;
        newSlab_g2 = (Slab_2 * N) / 30;
        newSlab_g3 = (Slab_3 * N) / 30;
        newSlab_g4 = (Slab_4 * N) / 30;
        newSlab_g5 = (Slab_5 * N) / 30;

        System.out.println("The New slab division is as follows:");
        System.out.println("Sale Price                                 Rs/MMBTU");
        System.out.println("Upto " + newSlab_g1+"hm3 per month         121.00Rs");
        System.out.println("Upto " + newSlab_g2+"hm3 per month         300.00Rs");
        System.out.println("Upto " + newSlab_g3+"hm3 per month         553.00Rs");
        System.out.println("Upto " + newSlab_g4+"hm3 per month         738.00Rs");
        System.out.println("Upto " + newSlab_g5+"hm3 per month         1107.00Rs");
        System.out.println("Upto " + newSlab_g5+"hm3 per month         1460.00Rs");

        if (i >= Slab_0 && i <= newSlab_g1)
        {
            System.out.println("The Slab Rate per MMBTU is " + Slab_Rate1);
            return Slab_rate = Slab_Rate1;
        }

        else if (i > newSlab_g1 && i <= newSlab_g2)
        {
            System.out.println("The Slab Rate per MMBTU is " + Slab_Rate2);
            Slab_rate = Slab_Rate2;

        }

        else if (i > newSlab_g2 && i <= newSlab_g3)
        {
            System.out.println("The Slab Rate per MMBTU is " + Slab_Rate3);
            Slab_rate = Slab_Rate3;

        }

        else if (i > newSlab_g3 && i <= newSlab_g4)
        {
            System.out.println("The Slab Rate per MMBTU is " + Slab_Rate4);
            Slab_rate = Slab_Rate4;

        }

        else if (i > newSlab_g4 && i <= newSlab_g5)
        {
            System.out.println("The Slab Rate per MMBTU is " + Slab_Rate5);
            Slab_rate = Slab_Rate5;
        }

        else
        {
            System.out.println("The Slab Rate per MMBTU is " + Slab_Rate6);
            Slab_rate = Slab_Rate6;
        }

        return Slab_rate;
    }

    /*check for else return statement*/
    double calculatingSlab (){

        if (N == 30)
        {
            S_R = SlabRate_E30 (Slab_rate);
            /* cout<< ("The Slab Rate per MMBTU is %d\n", S_R);*/
            return S_R;

        }


        else if (N < 30)
        {
            S_R = Slabrate_L30 (Slab_rate);
            /*cout<< ("The Slab Rate per MMBTU is %d\n", S_R);*/
            return S_R;

        }
        else if (N > 30)
        {
            S_R = SlabRate_M30 (Slab_rate);
            /* cout<< ("The Slab Rate per MMBTU is %d\n", S_R);*/
            return S_R;

        }
        else
        {
            System.out.println("You have entered an invalid nummber");
            return -1;
        }
    }

    /*Determining meter rent*/
    int meterRent_30 (){
        /*Meter Rent for 30 days */
        switch (meter)
        {
            case 1:
                /*cout<< ("Domestic_1\n"); */
                Meter_Rent = 20;
                break;
            case 2:
                /* cout<< ("Domestic_2\n"); */
                Meter_Rent = 20;
                break;
            case 3:
                /* cout<< ("Commercial\n"); */
                Meter_Rent = 100;
                break;
            case 4:
                /*cout<< ("Special Commercial\n"); */
                Meter_Rent = 100;
                break;
            case 5:
                /* cout<< ("Ice Factories\n"); */
                Meter_Rent = 100;
                break;
            case 6:
                /* cout<< ("General Industries\n"); */
                Meter_Rent = 100;
                break;
            case 7:
                /* cout<< ("Manufacturers\n"); */
                Meter_Rent = 100;
                break;
            case 8:
                /* cout<< ("CNG\n"); */
                Meter_Rent = 100;
                break;
            default:
                System.out.println("You have entered an invlaid meter type");
        }

        return Meter_Rent;
    }

    double meterRent_L30 (){
        /*Meter rent when less than 30 days */
        switch (meter)
        {
            case 1:
                /*cout<< ("Domestic_1\n"); */
                Meter_Rent = 20;
                NewMeterRent_L = (Meter_Rent * N) / 30;

                break;
            case 2:
                /*cout<< ("Domestic_2\n"); */
                Meter_Rent = 20;
                NewMeterRent_L = (Meter_Rent * N) / 30;
                break;
            case 3:
                /*cout<< ("Commercial\n"); */
                Meter_Rent = 100;
                NewMeterRent_L = (Meter_Rent * N) / 30;
                break;
            case 4:
                /* cout<< ("Special Commercial\n"); */
                Meter_Rent = 100;
                NewMeterRent_L = (Meter_Rent * N) / 30;
                break;
            case 5:
                /* cout<< ("Ice Factories\n"); */
                Meter_Rent = 100;
                NewMeterRent_L = (Meter_Rent * N) / 30;
                break;
            case 6:
                /*cout<< ("General Industries\n"); */
                Meter_Rent = 100;
                NewMeterRent_L = (Meter_Rent * N) / 30;
                break;
            case 7:
                /*cout<< ("Manufacturers\n"); */
                Meter_Rent = 100;
                NewMeterRent_L = (Meter_Rent * N) / 30;
                break;
            case 8:
                /*cout<< ("CNG\n"); */
                Meter_Rent = 100;
                NewMeterRent_L = (Meter_Rent * N) / 30;
                break;
            default:
                System.out.println("You have entered an invlaid meter type");
        }
        return NewMeterRent_L;
    }

    double meterRent_M30 (){
        /*Meter rent for more than 30 days */
        switch (meter)
        {
            case 1:
                /* cout<< ("Domestic_1\n"); */
                Meter_Rent = 20;
                NewMeterRent_M = (Meter_Rent * N) / 30;

                break;
            case 2:
                /*cout<< ("Domestic_2\n"); */
                Meter_Rent = 20;
                NewMeterRent_M = (Meter_Rent * N) / 30;
                break;
            case 3:
                /*cout<< ("Commercial\n"); */
                Meter_Rent = 100;
                NewMeterRent_M = (Meter_Rent * N) / 30;
                break;
            case 4:
                /*cout<< ("Special Commercial\n"); */
                Meter_Rent = 100;
                NewMeterRent_M = (Meter_Rent * N) / 30;
                break;
            case 5:
                /*cout<< ("Ice Factories\n"); */
                Meter_Rent = 100;
                NewMeterRent_M = (Meter_Rent * N) / 30;
                break;
            case 6:
                /* cout<< ("General Industries\n"); */
                Meter_Rent = 100;
                NewMeterRent_M = (Meter_Rent * N) / 30;
                break;
            case 7:
                /*cout<< ("Manufacturers\n"); */
                Meter_Rent = 100;
                NewMeterRent_M = (Meter_Rent * N) / 30;
                break;
            case 8:
                /*cout<< ("CNG\n"); */
                Meter_Rent = 100;
                NewMeterRent_M = (Meter_Rent * N) / 30;
                break;
            default:
                System.out.println("You have entered an invlaid meter type");
        }
        return NewMeterRent_M;
    }

    double meterRent (){
        /*Evaluating Final Meter Rent */
        if (N == 30)
        {
            meterRent_30 ();
            System.out.println("Your meter rent is "+Meter_Rent);
            MeterRent = Meter_Rent;
        }

        else if (N < 30)
        {
            meterRent_L30 ();
            System.out.println("Your meter rent is"+NewMeterRent_L);
            MeterRent = NewMeterRent_L;
        }

        else if (N > 30)
        {
            meterRent_M30 ();
            System.out.println("Your meter rent is"+NewMeterRent_M);
            MeterRent = NewMeterRent_M;
        }

        else
        {
            System.out.println("You have entered an invalid meter type.");
        }
        return MeterRent;
    }

    double minimumCharges_E30 (double Final_Gcharges){
        switch (meter)
        {
            case 1:
                if (Gas_Charges <= domestic1_MinCharges)
                {
                    Final_Gcharges = domestic1_MinCharges;
                }
                else
                {
                    Final_Gcharges = Gas_Charges;
                }

                /*cout<< ("\nThe final gas charges are %f\n", Final_Gcharges);*/


                break;
            case 2:
                if (Gas_Charges <= domestic2_MinCharges)
                {
                    Final_Gcharges = domestic2_MinCharges;
                }
                else
                {
                    Final_Gcharges = Gas_Charges;
                }

                /*cout<< ("\nThe final gas charges are %f\n", Final_Gcharges);*/



                break;
            case 3:
                if (Gas_Charges <= Commercial_MinCharges)
                {
                    Final_Gcharges = Commercial_MinCharges;
                }
                else
                {
                    Final_Gcharges = Gas_Charges;
                }

                /* cout<< ("\nThe final gas charges are %f\n", Final_Gcharges);*/



                break;
            case 4:
                if (Gas_Charges <= SpecialCommercial_MinCharges)
                {
                    Final_Gcharges = SpecialCommercial_MinCharges;
                }
                else
                {
                    Final_Gcharges = Gas_Charges;
                }

                /*cout<< ("\nThe final gas charges are %f\n", Final_Gcharges);*/

                break;
            case 5:
                if (Gas_Charges <= IceFactories_MinCharges)
                {
                    Final_Gcharges = IceFactories_MinCharges;
                }
                else
                {
                    Final_Gcharges = Gas_Charges;
                }

                /*cout<< ("\nThe final gas charges are %f\n", Final_Gcharges);*/


                break;
            case 6:
                if (Gas_Charges <= GeneralIndustries_MinCharges)
                {
                    Final_Gcharges = GeneralIndustries_MinCharges;
                }
                else
                {
                    Final_Gcharges = Gas_Charges;
                }

                /*cout<< ("\nThe final gas charges are %f\n", Final_Gcharges);*/


                break;
            case 7:
                if (Gas_Charges <= Manufacturers_MinCharges)
                {
                    Final_Gcharges = Manufacturers_MinCharges;
                }
                else
                {
                    Final_Gcharges = Gas_Charges;
                }

                /*cout<< ("\nThe final gas charges are %f\n", Final_Gcharges);*/

                break;
            case 8:
                if (Gas_Charges <= CNG_MinCharges)
                {
                    Final_Gcharges = CNG_MinCharges;
                }
                else
                {
                    Final_Gcharges = Gas_Charges;
                }

                /*cout<< ("\nThe final gas charges are %f\n", Final_Gcharges);*/
                break;
            default:
                System.out.println("You have entered an invlaid meter type");
        }
        return Final_Gcharges;
    }

    /*check for else return statement*/
    double minimumCharges_ML30 (double Final_Gcharges){

        newdomestic1_MinCharges = (domestic1_MinCharges * N) / 30;
        newdomestic2_MinCharges = (domestic2_MinCharges * N) / 30;
        newCommercial_MinCharges = (Commercial_MinCharges * N) / 30;
        newSpecialCommercial_MinCharges = (SpecialCommercial_MinCharges * N) / 30;
        newIceFactories_MinCharges = (IceFactories_MinCharges * N) / 30;
        newGeneralIndustries__MinCharges = (GeneralIndustries_MinCharges * N) / 30;
        newManufacturers_MinCharges = (Manufacturers_MinCharges * N) / 30;
        newCNG_MinCharge = (CNG_MinCharges * N) / 30;

        if (N < 30 || N > 30)
        {
            switch (meter)

            {
                case 1:
                    if (Gas_Charges <= newdomestic1_MinCharges)
                    {
                        Final_Gcharges = newdomestic1_MinCharges;
                    }
                    else
                    {
                        Final_Gcharges = Gas_Charges;
                    }

                    System.out.println("The final gas charges are"+Final_Gcharges);
                    break;
                case 2:
                    if (Gas_Charges <= newdomestic2_MinCharges)
                    {
                        Final_Gcharges = newdomestic2_MinCharges;
                    }
                    else
                    {
                        Final_Gcharges = Gas_Charges;
                    }

                    System.out.println("The final gas charges are"+Final_Gcharges);
                    break;
                case 3:
                    if (Gas_Charges <= newCommercial_MinCharges)
                    {
                        Final_Gcharges = newCommercial_MinCharges;
                    }
                    else
                    {
                        Final_Gcharges = Gas_Charges;
                    }

                    System.out.println("The final gas charges are"+Final_Gcharges);
                    break;
                case 4:
                    if (Gas_Charges <= newSpecialCommercial_MinCharges)
                    {
                        Final_Gcharges = newSpecialCommercial_MinCharges;
                    }
                    else
                    {
                        Final_Gcharges = Gas_Charges;
                    }

                    System.out.println("The final gas charges are"+Final_Gcharges);

                    break;
                case 5:
                    if (Gas_Charges <= newIceFactories_MinCharges)
                    {
                        Final_Gcharges = newIceFactories_MinCharges;
                    }
                    else
                    {
                        Final_Gcharges = Gas_Charges;
                    }

                    System.out.println("The final gas charges are"+Final_Gcharges);


                    break;
                case 6:
                    if (Gas_Charges <= newGeneralIndustries__MinCharges)
                    {
                        Final_Gcharges = newGeneralIndustries__MinCharges;
                    }
                    else
                    {
                        Final_Gcharges = Gas_Charges;
                    }

                    System.out.println("The final gas charges are"+Final_Gcharges);


                    break;
                case 7:
                    if (Gas_Charges <= newManufacturers_MinCharges)
                    {
                        Final_Gcharges =  newManufacturers_MinCharges;
                    }
                    else
                    {
                        Final_Gcharges = Gas_Charges;
                    }

                    System.out.println("The final gas charges are"+Final_Gcharges);

                    break;
                case 8:
                    if (Gas_Charges <= newCNG_MinCharge)
                    {
                        Final_Gcharges = newCNG_MinCharge;
                    }
                    else
                    {
                        Final_Gcharges = Gas_Charges;
                    }

                    System.out.println("The final gas charges are"+Final_Gcharges);


                    break;
                default:
                    System.out.println("You have entered an invlaid meter type");
            }
            return Final_Gcharges;
        }
        else{
            return -1;
        }
    }

    /*check for else return statement*/
    double finalMinimumCharges (double MinChg){
        if (N == 30)
        {
            MinChg = minimumCharges_E30 (Final_Gcharges);
            return MinChg;
        }
        else if (N < 30)
        {
            MinChg = minimumCharges_ML30 (Final_Gcharges);
            System.out.println("The Minimum Charges are "+MinChg);
            return MinChg;
        }
        else if (N > 30)
        {
            MinChg = minimumCharges_ML30 (Final_Gcharges);
            System.out.println("The Minimum Charges are "+MinChg);
            return MinChg;
        }
        else
        {
            System.out.println("You have entered an invalid nummber");
            return -1;
        }

    }

    double flat_Rate (double flatRate){
        switch (meter)
        {
            case 1:
                System.out.println("Invalid Command");
                break;
            case 2:
                flatRate=780.00;/*Per MMBTU Value*/
                break;
            case 3:
                flatRate=1283.00;
                break;
            case 4:
                System.out.println("Invalid Command");
                break;
            case 5:
                flatRate=1283.00;
                break;
            case 6:
                flatRate=1021.00;
                break;
            case 7:
                flatRate=786.00;
                break;
            case 8:
                flatRate=1283.00;
                break;
            default:
                System.out.println("You have entered an invlaid meter type");
        }
        return flatRate;

    }

    /*check for last return statement*/
    double Calculations(){
        double z;
        mmbtu = Calcualting_MMBTU ();
        z =flat_Rate(flatRate);
        totalGasCharges =z  * mmbtu;
        Gas_Charges = totalGasCharges;
        y = finalMinimumCharges (MinChg);
        if (Gas_Charges< y)


        { x = y + meter_R;
            Tax = (17 * x) / 100;
            totalPayble_Bill = Tax + x;

        }
        else
        {
            x=Gas_Charges+ meter_R;
            Tax = (17 * x) / 100;
            totalPayble_Bill = Tax + x;
        }

        System.out.println("The estimated bill is :"+totalPayble_Bill);
        return -1;
    }

    /*check for last return statement*/
    double finalCalculations(){
        mmbtu= Calcualting_MMBTU();
        Slabrate=calculatingSlab();
        meter_R=meterRent();

        y = finalMinimumCharges (MinChg);
        totalGasCharges=(Slabrate*mmbtu);
        Gas_Charges=totalGasCharges;
        if (totalGasCharges<y)
        {
            x = y + meter_R;
            Tax = (17 * x) / 100;
            totalPayble_Bill = Tax + x;
        }
        else{
            x=totalGasCharges+meter_R;
            /* cout<<("%f",x);*/
            Tax=(17*x)/100;
            totalPayble_Bill=Tax+x;
        }
        //output send to firebase
        System.out.println("The total payable bill is :"+totalPayble_Bill);
        return -1;
    }

}