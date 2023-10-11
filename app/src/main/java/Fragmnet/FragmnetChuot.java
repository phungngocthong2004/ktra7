package Fragmnet;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ktra7.R;

import java.util.ArrayList;

import Adapter.Chuot_Adapter;
import DAO.Chuot_Dao;
import DTO.Chuot_DTO;

public class FragmnetChuot extends Fragment {
    Button btmthem;
    RecyclerView rc_chuot;
    Chuot_Dao chuot_dao;
    ArrayList<Chuot_DTO>list;
    Chuot_Adapter chuot_adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmnetchuot,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btmthem=view.findViewById(R.id.btnthem);
        rc_chuot=view.findViewById(R.id.rc_chuot);

        chuot_dao=new Chuot_Dao(getContext());
        list=chuot_dao.getAll();
        chuot_adapter=new Chuot_Adapter(getContext(),list);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rc_chuot.setLayoutManager(linearLayoutManager);
        rc_chuot.setAdapter(chuot_adapter);

        btmthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                LayoutInflater inflater=getLayoutInflater();
                View v=inflater.inflate(R.layout.itemthem,null);
                builder.setView(v);
                Dialog dialog=builder.create();
                dialog.show();

                EditText edten=v.findViewById(R.id.edten);
                EditText edsolung=v.findViewById(R.id.edsoluong);
                EditText edchatlieu=v.findViewById(R.id.edchatlieu);
                EditText edmausac=v.findViewById(R.id.edmausac);
                Button btnthemm=v.findViewById(R.id.btnthemm);

                btnthemm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                        builder.setTitle("them");
                        builder.setMessage("ban muon them khong");
                        builder.setPositiveButton("co", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String ten =edten.getText().toString();
                             int soluong = Integer.parseInt(edsolung.getText().toString());
                                String chatlieu =edchatlieu.getText().toString();
                                String mausca =edmausac.getText().toString();

                                Chuot_Dao chuot_dao1=new Chuot_Dao(getContext());
                                Chuot_DTO chuot_dto=new Chuot_DTO(ten,soluong,chatlieu,mausca);

                                if (soluong<0|soluong>100){
                                    Toast.makeText(getContext(), "looi", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                if (chatlieu.length()<0|chatlieu.length()>10){
                                    Toast.makeText(getContext(), "looi", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                long id=chuot_dao1.add(chuot_dto);
                                if (id>0){
                                    Toast.makeText(getContext(), "them thanh cong", Toast.LENGTH_SHORT).show();
                                    list.clear();
                                    list.addAll(chuot_dao.getAll());
                                    chuot_adapter.notifyDataSetChanged();
                                }else{
                                    Toast.makeText(getContext(), "them thaat bai", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        builder.setNegativeButton("khong",null);
                        builder.show();
                    }
                });
            }
        });
    }
}
