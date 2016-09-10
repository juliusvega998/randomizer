package com.kintetsu.aspirev3.randomizer;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static com.kintetsu.aspirev3.randomizer.MainActivity.mList;

/**
 * Created by Aspire V3 on 8/21/2016.
 */

public class ObjectAdapter extends RecyclerView.Adapter<ObjectAdapter.ViewHolder> {

    private List<String> objectList;
    private Activity c;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView objectName;

        public ViewHolder(TextView view) {
            super(view);
            objectName = view;
        }
    }

    public ObjectAdapter(List<String> list, Activity c) {
        this.objectList = list;
        this.c = c;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.object_row, parent, false);
        final TextView tv = (TextView) view.findViewById(R.id.object);
        tv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(c);
                builder.setTitle("What do you want to do?")
                       .setItems(R.array.dialog_choices, new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int i) {
                               switch(i) {
                                   case 0: deleteClicked(); break;
                                   case 1: editClicked(); break;
                                   default: dialogInterface.cancel(); break;
                               }
                           }
                       }).show();
                return true;
            }

            private void deleteClicked() {
                AlertDialog.Builder builder = new AlertDialog.Builder(c);
                builder.setTitle("Are you sure?")
                       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int index) {
                               String s = tv.getText().toString();
                               boolean deleted = false;

                               for(int i = 0; i< mList.size(); i++) {
                                   if(s.equals(mList.get(i))) {
                                       deleted = true;
                                       mList.remove(i);
                                       c.finish();
                                       Toast.makeText(c, "Successfully deleted " + s, Toast.LENGTH_SHORT).show();
                                   }
                               }

                               if(!deleted) {
                                   Toast.makeText(c, "Something went wrong.", Toast.LENGTH_SHORT).show();
                               }
                           }
                       })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }).show();
            }

            private void editClicked() {
                final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(c);
                final android.app.AlertDialog dialog;

                final EditText etName = new EditText(c);
                final FrameLayout container = new FrameLayout(c);
                final FrameLayout.LayoutParams params = new  FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

                params.leftMargin = 50;
                params.rightMargin = 50;

                etName.setLayoutParams(params);
                etName.setHint("Enter new name");

                container.addView(etName);

                builder.setTitle("Edit string")
                       .setView(container)
                       .setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int index) {
                                String str = tv.getText().toString();
                                String name = etName.getText().toString();
                                mList.add(name);

                                for(int i = 0; i< mList.size(); i++) {
                                    if(str.equals(mList.get(i))) {
                                        mList.remove(i);
                                        Toast.makeText(
                                                c,
                                                "Successfully edited " + str,
                                                Toast.LENGTH_SHORT).show();
                                        c.finish();
                                    }
                                }
                            }
                       })
                       .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                            }
                       });

                dialog = builder.create();
                dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                dialog.show();
            }
        });

        final ViewHolder v = new ViewHolder(tv);
        return v;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int n) {
        holder.objectName.setText(objectList.get(n));
    }

    @Override
    public int getItemCount() {
        return objectList.size();
    }
}
