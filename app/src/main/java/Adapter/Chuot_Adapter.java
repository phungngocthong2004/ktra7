package Adapter;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ktra7.Noty;
import com.example.ktra7.R;

import java.util.ArrayList;
import java.util.Date;

import DAO.Chuot_Dao;
import DTO.Chuot_DTO;

public class Chuot_Adapter extends RecyclerView.Adapter<Chuot_Adapter.viewholder> {
    Context context;
    ArrayList<Chuot_DTO >list;
    Chuot_Dao chuot_dao;

    public Chuot_Adapter(Context context, ArrayList<Chuot_DTO> list) {
        this.context = context;
        this.list = list;
        chuot_dao=new Chuot_Dao(context);
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        View v=inflater.inflate(R.layout.dongchuot,parent,false);

        return new viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtid.setText("id: "+list.get(position).getId()+"");
        holder.txtten.setText("ten: "+list.get(position).getTen());
        holder.txtsoluong.setText("soluong : "+list.get(position).getSoluong()+"");
        holder.txtchatlieu.setText("chatlieu: "+list.get(position).getChatlieu());
        holder.txtmausac.setText("mausca: "+list.get(position).getMausac());
        holder.btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle("xoa");
                builder.setMessage("ban muon xoa khong");
                builder.setPositiveButton("co", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        long id=chuot_dao.xoa(list.get(position));
                        if (id>0){
                            list.remove(position);
                            Toast.makeText(context, "xoa thanh cong", Toast.LENGTH_SHORT).show();
                            notifyItemChanged(holder.getAdapterPosition());

                            Notification customNotification = new NotificationCompat.Builder(context, Noty.CHANEL_ID)
                                    .setSmallIcon(android.R.drawable.ic_delete)
                                    .setContentTitle("xóa")
                                    .setContentText("Bạn Đã xóa: ")
                                    .setColor(Color.BLUE)
                                    .setAutoCancel(true)
                                    .build();

// Khởi tạo Manager để quản lý notify
                            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);

// Cần kiểm tra quyền trước khi hiển thị notify
                            if (ActivityCompat.checkSelfPermission(context,
                                    android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {

                                // Gọi hộp thoại hiển thị xin quyền người dùng
                                ActivityCompat.requestPermissions((Activity) context,
                                        new String[]{Manifest.permission.POST_NOTIFICATIONS}, 999999);
                                return; // thoát khỏi hàm nếu chưa được cấp quyền
                            }
// nếu đã cấp quyền rồi thì sẽ vượt qua lệnh if trên và đến đây thì hiển thị notify
// mỗi khi hiển thị thông báo cần tạo 1 cái ID cho thông báo riêng
                            int id_notiy = (int) new Date().getTime();// lấy chuỗi time là phù hợp
//lệnh hiển thị notify
                            notificationManagerCompat.notify(id_notiy, customNotification);
                        }else{
                            Toast.makeText(context, "xoa thaat bai", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("khong",null);
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class viewholder extends RecyclerView.ViewHolder {
        TextView txtid,txtten,txtsoluong,txtchatlieu,txtmausac;
        Button btnsua,btnxoa;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            txtid=itemView.findViewById(R.id.id);
            txtten=itemView.findViewById(R.id.ten);
            txtsoluong=itemView.findViewById(R.id.soluong);
            txtchatlieu=itemView.findViewById(R.id.chatlieu);
            txtmausac=itemView.findViewById(R.id.mausac);
            btnsua=itemView.findViewById(R.id.btnsua);
            btnxoa=itemView.findViewById(R.id.btnxoa);
        }
    }
}
